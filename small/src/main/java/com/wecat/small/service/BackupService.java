package com.wecat.small.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecat.small.mapper.BackupMapper;

@Service
public class BackupService {
      
	@Autowired BackupMapper backupMapper;
	
	public int clean(){
		return backupMapper.clean();
	}
}
