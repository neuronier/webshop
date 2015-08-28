package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.BugMessageVO;
import hu.neuron.ier.core.entity.BugMessage;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class BugMessageConverter {
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	public BugMessageVO toVO(BugMessage dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, BugMessageVO.class);
	}

	public BugMessage toEntity(BugMessageVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, BugMessage.class);
	}

	public List<BugMessageVO> toVO(List<BugMessage> dtos) {
		if (dtos == null) {
			return null;
		}
		List<BugMessageVO> vos = new ArrayList<BugMessageVO>();
		for (BugMessage dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<BugMessage> toEntity(List<BugMessageVO> vos) {
		if (vos == null) {
			return null;
		}
		List<BugMessage> dtos = new ArrayList<BugMessage>();
		for (BugMessageVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}