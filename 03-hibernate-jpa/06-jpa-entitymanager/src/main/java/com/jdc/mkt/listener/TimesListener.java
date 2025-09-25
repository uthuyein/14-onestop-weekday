package com.jdc.mkt.listener;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class TimesListener {

	@PrePersist
	void prePersist(Object obj) {
		if(obj instanceof EnableTimesListener entity) {
			Times times = entity.getTimes();
			
			if(null == times) {
				times = new Times();
				entity.setTimes(times);
			}
			times.setCreateTime(LocalDateTime.now());
		}
	}
	
	@PreUpdate
	void preUpdate(Object obj) {
		if(obj instanceof EnableTimesListener entity) {
			Times times = entity.getTimes();
			
			if(null == times) {
				times = new Times();
				entity.setTimes(times);
			}
			times.setUpdateTime(LocalDateTime.now());
		}
	}
}
