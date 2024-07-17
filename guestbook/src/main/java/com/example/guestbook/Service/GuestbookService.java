package com.example.guestbook.Service;

import com.example.guestbook.Dto.GuestbookDTO;
import com.example.guestbook.Dto.PageRequestDTO;
import com.example.guestbook.Dto.PageResponseDTO;
import com.example.guestbook.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDTO guestbookDTO);

    PageResponseDTO<GuestbookDTO, Guestbook> list(PageRequestDTO pageRequestDTO);

    GuestbookDTO read(Long gno);

    void update(GuestbookDTO guestbookDTO);

    void remove(Long gno);


    default Guestbook dtoToEntity(GuestbookDTO guestbookDTO) {

        Guestbook guestbook = Guestbook.builder()
                .gno(guestbookDTO.getGno())
                .content(guestbookDTO.getContent())
                .writer(guestbookDTO.getWriter())
                .build();

        return guestbook;
    }

    default GuestbookDTO entityToDto(Guestbook guestbook) {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .gno(guestbook.getGno())
                .content(guestbook.getContent())
                .writer(guestbook.getWriter())
                .regDate(guestbook.getRegDate())
                .modDate(guestbook.getModDate())
                .build();

        return guestbookDTO;
    }


}
