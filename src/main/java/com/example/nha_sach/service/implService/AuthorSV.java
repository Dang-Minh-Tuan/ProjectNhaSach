package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.AuthorDTO;
import com.example.nha_sach.entities.Author;
import com.example.nha_sach.mapper.AuthorMP;
import com.example.nha_sach.repository.AuthorRP;
import com.example.nha_sach.service.IAuthorSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorSV implements IAuthorSV {
    @Autowired
    private AuthorRP authorRP;
    @Autowired
    private AuthorMP authorMP;

    @Override
    public Page<AuthorDTO> getAll(Pageable pageable) {
        Page<AuthorDTO> authorDTOS = authorRP.findAll(pageable).map(x -> authorMP.toDTO(x));
        return authorDTOS;
    }

    @Override
    public boolean Save(AuthorDTO authorDTO) {
        try {
            authorRP.save(new Author().toEntity(authorDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(AuthorDTO authorDTO) {
        try {
            authorRP.save(new Author().toEntity(authorDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(AuthorDTO authorDTO) {
        authorRP.delete(new Author().toEntity(authorDTO));
        return true;
    }

    public List<AuthorDTO> getAllListAuthor(){
        return authorRP.findAll().stream().map(x -> authorMP.toDTO(x)).toList();
    }

    public String checkSizeAuthor(String name){
        // Tạo ra chuỗi code rỗng
        String code = "";
        // Tạo ra 1 mảng chứa các Từ được cắt ra từ name được chuyền vào
        String list[] =  name.split(" ");
        for (String s : list) { // For each để lấy ra từng từ đã được tách ra khỏi chuỗi name
            if (list.length == 1){ // nếu name điền vào chỉ có 1 từ thì lấy luôn cả từ đấy
                code += s;
            }else if (!s.equals("") && !s.equals(null)) { // Check điều kiện để lấy ra các chữ cái  đầu tiên của từng chữ vừa được tách ra = split
                code += String.valueOf(s.charAt(0)); // Cộng từng chữ cái đầu tiên đấy lại với nhau tạo thành chuỗi mới rồi gán vào biến code
            }
        }
        List<AuthorDTO> authorDTOS = getAllListAuthor(); // Lấy ra tất cả phần từ có trong Category
        // Check xem list Cate có rỗng hay ko
        if (authorDTOS.size() == 0){
            String id = String.valueOf(1); // Rỗng thì code + 1
            code += id;
        }else {
            String regex = "[^0-9]";
            String index = ""; // Tạo ra 1 chuỗi index trống để tí nữa sẽ chứa các ptu số được cắt ra
            String[] code_last = authorDTOS.get(authorDTOS.size() -1).getCode().split(regex); // Lấy ra phần số trong code cuối cùng của list Cate
            for (String s : code_last) {
                index += s; // Hứng vào chuỗi index phần số vừa đc lấy ra từ chuỗi
            }
            int i = Integer.parseInt(index) + 1; // ép kiểu cho index để cộng với 1 -> tạo ra số tăng dần lớn hơn 1 đvi so với code đứng trước
            code += i; // cộng chuỗi để hoàn thành code
        }
        return code;
    }

    public List<AuthorDTO> findAuthorById(Long id){
        List<AuthorDTO> authorDTOS = authorRP.findAuthorById(id).stream().map(x -> authorMP.toDTO(x)).toList();

        return authorDTOS;
    }

    public List<AuthorDTO> findAuthorByName(String name){
        return authorRP.findAuthorByName(name).stream().map(x -> authorMP.toDTO(x)).toList();
    }

    public List<AuthorDTO> CheckAndSaveAuthorInProduct(String name){
        List<AuthorDTO> authorDTOS = findAuthorByName(name);
            if (authorDTOS.size() == 0){
                AuthorDTO authorDTO = new AuthorDTO();
                String code = checkSizeAuthor(name);
                authorDTO.setCode(code);
                authorDTO.setName(name);
                Save(authorDTO);
                List<AuthorDTO> authorDTOList = findAuthorByName(authorDTO.getName());
                return authorDTOList;
            }
        return authorDTOS;
    }

    public String updateCodeAuthor(String name, String code){
        String codeUpdate = "";
        String list[] =  name.split(" ");
        for (String s : list) { // For each để lấy ra từng từ đã được tách ra khỏi chuỗi name
            if (list.length == 1){ // nếu name điền vào chỉ có 1 từ thì lấy luôn cả từ đấy
                codeUpdate += s;
            }else if (!s.equals("") && !s.equals(null)) { // Check điều kiện để lấy ra các chữ cái  đầu tiên của từng chữ vừa được tách ra = split
                codeUpdate += String.valueOf(s.charAt(0)); // Cộng từng chữ cái đầu tiên đấy lại với nhau tạo thành chuỗi mới rồi gán vào biến code
            }
        }
        String regex = "[^0-9]";
        String index = ""; // Tạo ra 1 chuỗi index trống để tí nữa sẽ chứa các ptu số được cắt ra
        String[] old_code = code.split(regex); // Lấy ra phần số trong code cuối cùng của list Cate
        for (String s : old_code) {
            index += s; // Hứng vào chuỗi index phần số vừa đc lấy ra từ chuỗi
        }
        codeUpdate += index; // cộng chuỗi để hoàn thành code
        return codeUpdate;
    }
}
