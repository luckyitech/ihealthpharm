package com.ihealthpharm.uniquecode.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ihealthpharm.exception.IHealthPharmException;
import com.ihealthpharm.uniquecode.dao.UniqueCodeRepository;
import com.ihealthpharm.uniquecode.model.UniqueCodeModel;
import com.ihealthpharm.uniquecode.service.UniqueCodeService;

@Service
public class UniqueCodeServiceImple implements UniqueCodeService {

	@Autowired
	UniqueCodeRepository uniqueCodeRepository;
	
	@Override
	public UniqueCodeModel save(UniqueCodeModel uniqueCode) {
		
		return uniqueCodeRepository.save(uniqueCode);
	}

	@Override
	public String findByUniqueCodeName(String uniqueCodeName) {
		UniqueCodeModel uniqueCodeRes = uniqueCodeRepository.findByUniqueCodeName(uniqueCodeName);
		Integer newUniqueCodeNumber=uniqueCodeRes.getUniqueCodeNumber()+1;
		uniqueCodeRes.setUniqueCodeNumber(newUniqueCodeNumber);
		uniqueCodeRes = uniqueCodeRepository.save(uniqueCodeRes);
		
		if (!Objects.nonNull(uniqueCodeRes)) {
			throw new IHealthPharmException(uniqueCodeName+" Generation Exception", HttpStatus.NOT_FOUND);
		}
		
		String uniqueNumber ="DP"+uniqueCodeName.toUpperCase();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");  
	    Date date = new Date();  
	    String currentDate[] = formatter.format(date).split("-");
	    
		if(uniqueCodeRes.getUniqueCodeNumber().toString().length() == 1) {
			uniqueNumber += currentDate[0]+currentDate[1]+currentDate[2]+"000"+uniqueCodeRes.getUniqueCodeNumber().toString(); 
		}
		else if(uniqueCodeRes.getUniqueCodeNumber().toString().length() == 2)
		{
			uniqueNumber += currentDate[0]+currentDate[1]+currentDate[2]+"00"+uniqueCodeRes.getUniqueCodeNumber().toString();
		}
		else if(uniqueCodeRes.getUniqueCodeNumber().toString().length() == 3)
		{
			uniqueNumber += currentDate[0]+currentDate[1]+currentDate[2]+"0"+uniqueCodeRes.getUniqueCodeNumber().toString();
		}
		else
		{
			uniqueNumber += currentDate[0]+currentDate[1]+currentDate[2]+uniqueCodeRes.getUniqueCodeNumber().toString();
		}

		return uniqueNumber;
	}

}
