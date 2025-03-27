package com.example.IPM.Coures.Project.general.copy;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;



@Service
public class CopyService {

    @Scheduled(cron = "0 0 0 * * ?")
    public String createBackup() {
        String host = "localhost"; // Хост базы данных
        String port = "3306"; // Порт базы данных
        String database = "ipmcourseproject"; // Имя базы данных
        String user = "root"; // Пользователь базы данных
        String password = "root"; // Пароль базы данных

        // Путь к mysqldump (указывать явно, если он не в PATH)
        String mysqldumpPath = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe";

        // Путь для сохранения дампа
        String backupPath = "D:\\Бгуир\\Это может быть полезно\\IPM Coures Project\\IPM-course-project\\Server\\IPM-Coures-Project\\src\\main\\java\\com\\example\\IPM\\Coures\\Project\\general\\copy\\script\\backup\\backup.sql";

        // Формируем команду
        String command = String.format(
                "\"%s\" --host=%s --port=%s --user=%s --password=%s --databases %s -r \"%s\"",
                mysqldumpPath, host, port, user, password, database, backupPath
        );

        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return ("Резервное копирование успешно завершено: " + backupPath);
            } else {
                return("Ошибка при резервном копировании. Код выхода: " + exitCode);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}

