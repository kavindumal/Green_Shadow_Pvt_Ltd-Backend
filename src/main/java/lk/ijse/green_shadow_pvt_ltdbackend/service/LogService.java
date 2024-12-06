package lk.ijse.green_shadow_pvt_ltdbackend.service;

import lk.ijse.green_shadow_pvt_ltdbackend.dto.LogDTO;

import java.util.List;

public interface LogService {
    void saveLog(LogDTO log);
    void updateLog(String id, LogDTO log);
    LogDTO searchLog(String id);
    boolean deleteLog(String id);
    List<LogDTO> getAllLogs();
}
