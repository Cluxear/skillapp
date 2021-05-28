package com.tw.skillapp.service.impl;

import com.tw.skillapp.service.DomainService;
import com.tw.skillapp.domain.Domain;
import com.tw.skillapp.repository.DomainRepository;
import com.tw.skillapp.service.dto.DomainDTO;
import com.tw.skillapp.service.mapper.DomainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Domain}.
 */
@Service
@Transactional
public class DomainServiceImpl implements DomainService {

    private final Logger log = LoggerFactory.getLogger(DomainServiceImpl.class);

    private final DomainRepository domainRepository;

    private final DomainMapper domainMapper;

    public DomainServiceImpl(DomainRepository domainRepository, DomainMapper domainMapper) {
        this.domainRepository = domainRepository;
        this.domainMapper = domainMapper;
    }

    @Override
    public DomainDTO save(DomainDTO domainDTO) {
        log.debug("Request to save Domain : {}", domainDTO);
        Domain domain = domainMapper.toEntity(domainDTO);
        domain = domainRepository.save(domain);
        return domainMapper.toDto(domain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DomainDTO> findAll() {
        log.debug("Request to get all Domains");
        return domainRepository.findAll().stream()
            .map(domainMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DomainDTO> findOne(Long id) {
        log.debug("Request to get Domain : {}", id);
        return domainRepository.findById(id)
            .map(domainMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Domain : {}", id);
        domainRepository.deleteById(id);
    }
}
