package com.alienlab.my.module.book.service.imp;

import com.alienlab.my.entity.WebConfiguration;
import com.alienlab.my.module.book.service.WebConfigurationService;
import com.alienlab.my.repository.WebConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhuliang on 2017/12/6.
 */
@Service
public class WebConfigurationImpl implements WebConfigurationService {
    @Autowired
    private WebConfigurationRepository webConfigurationRepository;


    @Override
    public WebConfiguration getWebConfig() throws Exception {
        WebConfiguration webConfiguration ;
        webConfiguration = webConfigurationRepository.findOne(1L);

        if(webConfiguration == null){
            throw new Exception("没有对应配置！");
        }
        return  webConfiguration;
    }
}
