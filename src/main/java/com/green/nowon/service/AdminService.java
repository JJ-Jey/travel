package com.green.nowon.service;

import org.springframework.ui.Model;

public interface AdminService {

	void findAll(Model model);

	void detailProcess(long pno, Model model);

}
