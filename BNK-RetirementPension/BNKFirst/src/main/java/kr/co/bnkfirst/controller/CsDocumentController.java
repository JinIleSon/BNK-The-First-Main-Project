package kr.co.bnkfirst.controller;

import kr.co.bnkfirst.dto.DocumentDTO;
import kr.co.bnkfirst.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class CsDocumentController {

    private final DocumentService documentService;

    //전체목록
    @GetMapping
    public List<DocumentDTO> getAllDocuments() {
        log.info("문서 목록 요청");
        return documentService.getAllDocuments();
    }

    //검색
    @GetMapping("/search")
    public List<DocumentDTO> searchDocuments(@RequestParam String keyword) {
        log.info("문서 검색 요청 keyword={}", keyword);
        return documentService.searchDocuments(keyword);
    }
}
