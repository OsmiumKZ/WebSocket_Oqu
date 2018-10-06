/*
 * Copyright 2018 EDVAM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kz.eid;

import kz.eid.jdbc.JDBCGET;
import kz.eid.jdbc.JDBCPOST;
import kz.eid.jdbc.JDBCPUT;
import kz.eid.utils.HerokuKey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.*;

public class Main {

    /* Подключается к базе данных. */
    private static Connection connection;

    /**
     * Запускает WebSocket
     * Port и запросы настроенны именно в этом методе.
     */
    public static void main(String[] args) {

        /* Изменяет port. */
        port(getHerokuAssignedPort());

        /* Подключение к БД. */
        connectDB();

        /* GET запрос на получение статуса WebSocket'а */
        get("/", (req, res) -> "Status: Online");

        /* GET запросы */
        getAPI();

        /* POST запросы */
        postAPI();

        /* PUT запросы */
        putAPI();
    }

    /**
     * GET запросы.
     */
    private static void getAPI() {

        /*
         * Получить факультеты.
         *
         * https://example.com/faculty
         */
        get("/faculty", (request, response) -> JDBCGET.getFaculty(connection));

        /*
         * Получить специальности.
         *
         * https://example.com/specialty
         */
        get("/specialty", (request, response) -> JDBCGET.getSpecialty(connection));

        /*
         * Получить группы.
         *
         * https://example.com/group
         */
        get("/group", (request, response) -> JDBCGET.getGroup(connection));

        /*
         * Получить расписание.
         *
         * https://example.com/schedule
         */
        get("/schedule", (request, response) -> JDBCGET.getSchedule(connection));

        /*
         * Получить преподавателя.
         *
         * https://example.com/teacher
         */
        get("/teacher", (request, response) -> JDBCGET.getTeacher(connection));

        /*
         * Получить всех преподавателей.
         *
         * https://example.com/teacher/all
         */
        path("/teacher", () -> get("/all", (request, response) -> JDBCGET.getAll(connection)));

        /*
         * Получить список предметов.
         *
         * https://example.com/list
         */
        get("/list", (request, response) -> JDBCGET.getList(connection));
    }

    /**
     * POST запросы.
     */
    private static void postAPI() {

        /*
         * Создает расписание для группы.
         *
         * https://example.com/schedule
         */
        post("/schedule", (request, response) -> JDBCPOST.getSchedule(connection));

        /*
         * Создает замену для конкретного предмета в расписании группы.
         *
         * https://example.com/change
         */
        post("/change", (request, response) -> JDBCPOST.getChange(connection));
    }

    /**
     * PUT запросы.
     */
    private static void putAPI() {

        /*
         * Вносит изменения в расписании группы.
         *
         * https://example.com/schedule
         */
        put("/schedule", (request, response) -> JDBCPUT.putSchedule(connection));

        /*
         * Вносит изменения в замене группы.
         *
         * https://example.com/change
         */
        put("/change", (request, response) -> JDBCPUT.putChange(connection));
    }

    /**
     * Подключение к БД.
     */
    private static void connectDB() {

        try {
            connection = DriverManager.getConnection(HerokuKey.url, HerokuKey.login, HerokuKey.password);
        } catch (SQLException e) {

            System.out.println("Error SQL Connecting");
        }
    }

    /**
     * Настройка port'а
     * С heroku.com приходит ответ зарег. порта для WebSocket'а
     *
     * @return если сервер запущен на стороне heroku.com, то будет использован port от heroku.com.
     */
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }
}

