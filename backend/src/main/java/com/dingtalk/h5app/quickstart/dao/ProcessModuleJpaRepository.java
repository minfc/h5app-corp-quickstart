package com.dingtalk.h5app.quickstart.dao;


import com.dingtalk.h5app.quickstart.domain.entity.ProcessModuleVoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessModuleJpaRepository extends JpaRepository<ProcessModuleVoEntity, String> ,JpaSpecificationExecutor {
}


