package com.green.nowon.openapi.alarm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DATA {
	
	private int alarm_lvl;
	private String continent_cd;
	private String continent_eng_nm;
	private String continent_nm;
	private String country_eng_nm;
	private String country_iso_alp2;
	private String country_nm;
	private String dang_map_download_url;
	private String flag_download_url;
	private String map_download_url;
	private String region_ty;
	private String remark;
	private String written_dt;

}
