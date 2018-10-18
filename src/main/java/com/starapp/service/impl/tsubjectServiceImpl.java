package com.starapp.service.impl;

import com.starapp.common.BackMessage;
import com.starapp.dao.TsubjectMapper;
import com.starapp.entity.StandardSubject;
import com.starapp.entity.StandardSubjectFinance;
import com.starapp.entity.Tsubject;
import com.starapp.service.TsubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date:2018/9/2511:54
 * @author:Allen
 * @description:
 */
@Service
public class tsubjectServiceImpl implements TsubjectService {

    @Autowired
    private TsubjectMapper tsubjectMapper;

    @Override
    public List<StandardSubject> getAllStandard_subject() {
        return tsubjectMapper.getAllStandard_subject();
    }

    @Override
    public List<StandardSubjectFinance> getAllStandard_subject_finance() {
        return tsubjectMapper.getAllStandard_subject_finance();
    }

    @Override
    public List<?> getAllBalance_sheet() {
        return tsubjectMapper.getAllBalance_sheet();
    }

    @Override
    public List<?> getAllProfit_loss() {
        return tsubjectMapper.getAllProfit_loss();
    }

    @Override
    public List<Tsubject> getAllTsubjectService() {
        return tsubjectMapper.getAllTsubject();
    }
}
