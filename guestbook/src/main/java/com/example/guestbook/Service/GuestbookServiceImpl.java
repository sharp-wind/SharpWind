package com.example.guestbook.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.guestbook.Dto.GuestbookDTO;
import com.example.guestbook.Dto.PageRequestDTO;
import com.example.guestbook.Dto.PageResponseDTO;
import com.example.guestbook.Repository.GuestbookRepository;
import com.example.guestbook.entity.Guestbook;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO guestbookDTO) {
        log.info("register -> " + guestbookDTO);

        Guestbook guestbook = dtoToEntity(guestbookDTO);

        Guestbook result = guestbookRepository.save(guestbook);

        log.info("registered -> " + result);

        return result.getGno();
    }

    @Override
    public PageResponseDTO<GuestbookDTO, Guestbook> list(PageRequestDTO pageRequestDTO) {
        log.info("get guestbook list");

        Sort sort = Sort.by("gno").descending();

        Pageable pageable = pageRequestDTO.getPageable(sort);

        Page<Guestbook> findAllResult = guestbookRepository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = guestbook -> entityToDto(guestbook);

        return new PageResponseDTO<>(findAllResult, fn);
    }

    @Override
    public GuestbookDTO read(Long gno) {
        log.info("get guestbook (gno : " + gno + ")");

        Optional<Guestbook> findByIdResult = guestbookRepository.findById(gno);

        return findByIdResult.isPresent() ? entityToDto(findByIdResult.get()) : null;
    }

    @Override
    public void update(GuestbookDTO guestbookDTO) {
        log.info("update guestbook (gno : " + guestbookDTO.getGno() + ")");

        guestbookRepository.findById(guestbookDTO.getGno())
                .ifPresent( guestbook -> {
                            guestbook.upateContent(guestbookDTO.getContent());
                        }
                );

    }

    @Override
    public void remove(Long gno) {
        log.info("remove guestbook (gno : " + gno + ")");

        guestbookRepository.deleteById(gno);
    }
}
