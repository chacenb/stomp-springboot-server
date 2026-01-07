package com.chace.microservice.controllers;

import com.chace.microservice.model.dto.ProvinceDto;
import com.chace.microservice.model.entity.Province;
import com.chace.microservice.model.utils.FtaResponse;
import com.chace.microservice.controllers.ifaces.IProvinceController;
import com.chace.microservice.services.ProvinceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j @RestController @RequiredArgsConstructor @CrossOrigin("*")
public class ProvinceController implements IProvinceController {

  private final ProvinceService provinceService;

  @Override
  public FtaResponse<List<ProvinceDto>> findAll(String title, Boolean deleted, Integer startIndex, Integer nbRows) {
    Page<Province> items = provinceService.findAll(title, deleted, startIndex, nbRows);
    log.info("items = {}", items);
    return FtaResponse.<List<ProvinceDto>>builder()
                      .timeStamp(ZonedDateTime.now())
                      .status(HttpStatus.OK)
                      .message("content inside data")
                      .data(items.get().map(ProvinceDto::toDto).toList())
                      .total((int) items.getTotalElements())
                      .page(items.getTotalPages())
                      .build();
  }

  @Override
  public FtaResponse<ProvinceDto> getOne(Long idProvince, Boolean deleted) {
    return FtaResponse.<ProvinceDto>builder()
                      .timeStamp(ZonedDateTime.now())
                      .status(HttpStatus.OK)
                      .message("content inside data")
                      .data(ProvinceDto.toDto(provinceService.getOne(idProvince, deleted)))
                      .build();
  }

  @Override
  public FtaResponse<ProvinceDto> save(ProvinceDto provinceDto) {
    return FtaResponse.<ProvinceDto>builder()
                      .timeStamp(ZonedDateTime.now())
                      .status(HttpStatus.OK)
                      .message("content inside data")
                      .data(ProvinceDto.toDto(provinceService.saveOrUpdateProvince(provinceDto)))
                      .build();
  }

  @Override
  public FtaResponse<ProvinceDto> update(ProvinceDto provinceDto, Long idProvince) {
    provinceDto.setIdProvince(idProvince);
    return FtaResponse.<ProvinceDto>builder()
                      .timeStamp(ZonedDateTime.now())
                      .status(HttpStatus.OK)
                      .message("content inside data")
                      .data(ProvinceDto.toDto(provinceService.saveOrUpdateProvince(provinceDto)))
                      .build();
  }

  @Override
  public FtaResponse<Boolean> delete(Long idProvince) {
    return FtaResponse.<Boolean>builder()
                      .timeStamp(ZonedDateTime.now())
                      .status(HttpStatus.OK)
                      .message("content inside data")
                      .data((Objects.nonNull(provinceService.delete(idProvince))))
                      .build();
  }
}
