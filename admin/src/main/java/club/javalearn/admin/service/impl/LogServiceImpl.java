package club.javalearn.admin.service.impl;

import club.javalearn.admin.model.Log;
import club.javalearn.admin.repository.LogRepository;
import club.javalearn.admin.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/6/22
 * Time: 下午6:01
 * Description: No Description
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void saveLog(Log log) {
        logRepository.save(log);
    }
}
