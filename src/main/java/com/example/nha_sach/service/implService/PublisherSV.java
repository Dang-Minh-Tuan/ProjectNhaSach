package com.example.nha_sach.service.implService;

import com.example.nha_sach.dto.PublisherDTO;
import com.example.nha_sach.entities.Publisher;
import com.example.nha_sach.mapper.PublisherMP;
import com.example.nha_sach.repository.PublisherRP;
import com.example.nha_sach.service.IPublisherSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherSV implements IPublisherSV {
    @Autowired
    private PublisherRP publisherRP;
    @Autowired
    private PublisherMP publisherMP;

    @Override
    public Page<PublisherDTO> getAll(Pageable pageable) {
        Page<PublisherDTO> publisherDTOS = publisherRP.findAll(pageable).map(x -> publisherMP.toDTO(x));
        return publisherDTOS;
    }

    @Override
    public boolean Save(PublisherDTO publisherDTO) {
        try {
            publisherRP.save(new Publisher().toEntity(publisherDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(PublisherDTO publisherDTO) {
        try {
            publisherRP.save(new Publisher().toEntity(publisherDTO));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(PublisherDTO publisherDTO) {
        publisherRP.delete(new Publisher().toEntity(publisherDTO));
        return true;
    }

    public List<PublisherDTO> getAllListPub(){
        return publisherRP.findAll().stream().map(x -> publisherMP.toDTO(x)).toList();
    }

    public String checkSizePub(String name){
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
        List<PublisherDTO> categoryDTOS = getAllListPub(); // Lấy ra tất cả phần từ có trong Category
        // Check xem list Cate có rỗng hay ko
        if (categoryDTOS.size() == 0){
            String id = String.valueOf(1); // Rỗng thì code + 1
            code += id;
        }else {
            String regex = "[^0-9]";
            String index = ""; // Tạo ra 1 chuỗi index trống để tí nữa sẽ chứa các ptu số được cắt ra
            String[] code_last = categoryDTOS.get(categoryDTOS.size() -1).getCode().split(regex); // Lấy ra phần số trong code cuối cùng của list Cate
            for (String s : code_last) {
                index += s; // Hứng vào chuỗi index phần số vừa đc lấy ra từ chuỗi
            }
            int i = Integer.parseInt(index) + 1; // ép kiểu cho index để cộng với 1 -> tạo ra số tăng dần lớn hơn 1 đvi so với code đứng trước
            code += i; // cộng chuỗi để hoàn thành code
        }
        return code;
    }

    public List<PublisherDTO> findPublisherById(Long id){
        List<PublisherDTO> publisherDTOS = publisherRP.findPublisherById(id).stream().map(x -> publisherMP.toDTO(x)).toList();
        return publisherDTOS;
    }

    public List<PublisherDTO> findPublishersByName(String name){
        List<PublisherDTO> publisherDTOS = publisherRP.findPublishersByName(name).stream().map(x -> publisherMP.toDTO(x)).toList();
        return publisherDTOS;
    }

    public PublisherDTO CheckAndSavePublisherInProduct(String name){
            if (findPublishersByName(name).size() == 0){
                PublisherDTO publisherDTO = new PublisherDTO();
                String code = checkSizePub(name);
                publisherDTO.setCode(code);
                publisherDTO.setName(name);
                Save(publisherDTO);
                return findPublishersByName(publisherDTO.getName()).get(0);
            }
        return findPublishersByName(name).get(0);
    }

    public String updateCodePub(String name,String code){
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
