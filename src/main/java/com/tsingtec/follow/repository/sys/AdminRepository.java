package com.tsingtec.follow.repository.sys;

import com.tsingtec.follow.entity.sys.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, JpaSpecificationExecutor<Admin> {
    Admin findByLoginName(String loginName);

    @Modifying
    @Transactional
    @Query("delete from Admin a where a.id in (?1)")
    void deleteBatch(@Param(value = "ids") List<Integer> ids);
}
