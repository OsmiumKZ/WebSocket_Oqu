/*
 * Copyright 2018 Osmium
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

package kz.osmium.oqu.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kz.osmium.account.main.util.TokenCheck;
import kz.osmium.account.main.util.TokenGen;
import kz.osmium.main.util.StatusResponse;
import kz.osmium.oqu.gson.MarkJSON;
import kz.osmium.oqu.statement.GETStatement;
import kz.osmium.oqu.statement.POSTStatement;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OquPOST {

    /**
     * Создает факультет.
     * Используется таблица "faculty"
     *
     * @param connection
     * @return
     */
    public static String postFaculty(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postFaculty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает факультет.
     * Используется таблица "specialty"
     *
     * @param connection
     * @return
     */
    public static String postSpecialty(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("name") != null &&
                    request.queryParams("id_faculty") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postSpecialty());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_faculty")));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает группу.
     * Используется таблица "group"
     *
     * @param connection
     * @return
     */
    public static String postGroup(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("name") != null &&
                    request.queryParams("id_specialty") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postGroup());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_specialty")));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает куратора.
     * Используется таблица "search"
     *
     * @param connection
     * @return
     */
    public static String postCurator(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("group") != null &&
                    request.queryParams("teacher") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postCurator());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("group")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("teacher")));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает кабинет.
     * Используется таблица "room"
     *
     * @param connection
     * @return
     */
    public static String postRoom(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postRoom());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает рейтинг студента.
     * Используется таблица "rating"
     *
     * @param connection
     * @return
     */
    public static String postRating(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("id_subject") != null &&
                    request.queryParams("id_account") != null &&
                    request.queryParams("num") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postRating());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_subject")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_account")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("num")));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает оценку студентам.
     * Используется таблица "mark"
     *
     * @param connection
     * @return
     */
    public static String postMark(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("id_subject") != null) {

                try {
                    Type type = new TypeToken<List<MarkJSON>>() {
                    }.getType();
                    ArrayList<MarkJSON> list = new Gson().fromJson(request.body(), type);

                    for (MarkJSON markJSON : list) {
                        PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(GETStatement.getRatingCurrent());

                        preparedStatement.setInt(1, markJSON.getIdAccount());
                        preparedStatement.setInt(2, Integer.parseInt(request.queryParams("id_subject")));

                        ResultSet resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            PreparedStatement preparedStatement2 = connection.get("oqu").prepareStatement(POSTStatement.postMark());

                            preparedStatement2.setInt(1, resultSet.getInt("id_rating"));
                            preparedStatement2.setInt(2, markJSON.getN());
                            preparedStatement2.setInt(3, markJSON.getMark());
                            preparedStatement2.execute();
                        }
                    }

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает аккаунт.
     * Используется таблица "account"
     *
     * @param connection
     * @return
     */
    public static String postAccount(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("name") != null &&
                    request.queryParams("login") != null &&
                    request.queryParams("t") != null &&
                    request.queryParams("pass") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postAccount());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("t")));
                    preparedStatement.setString(3, request.queryParams("login"));
                    preparedStatement.setString(4, request.queryParams("pass"));
                    preparedStatement.setString(11, TokenGen.generate(request.queryParams("login")));

                    if (request.queryParams("s_name") != null)
                        preparedStatement.setString(5, request.queryParams("s_name"));
                    else
                        preparedStatement.setNull(5, Types.VARCHAR);

                    if (request.queryParams("l_name") != null)
                        preparedStatement.setString(6, request.queryParams("l_name"));
                    else
                        preparedStatement.setNull(6, Types.VARCHAR);

                    if (request.queryParams("phone") != null)
                        preparedStatement.setString(7, request.queryParams("phone"));
                    else
                        preparedStatement.setNull(7, Types.VARCHAR);

                    if (request.queryParams("email") != null)
                        preparedStatement.setString(8, request.queryParams("email"));
                    else
                        preparedStatement.setNull(8, Types.VARCHAR);

                    if (request.queryParams("id_group") != null &&
                            Integer.parseInt(request.queryParams("t")) == 1)
                        preparedStatement.setInt(9, Integer.parseInt(request.queryParams("id_group")));
                    else
                        preparedStatement.setNull(9, Types.INTEGER);

                    if (request.queryParams("id_room") != null &&
                            Integer.parseInt(request.queryParams("t")) == 2)
                        preparedStatement.setInt(10, Integer.parseInt(request.queryParams("id_room")));
                    else
                        preparedStatement.setNull(10, Types.INTEGER);

                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает предмет для расписания.
     * Используется таблица "list_subject"
     *
     * @param connection
     * @return
     */
    public static String postSubjectItem(HashMap<String, Connection> connection, Request request, Response response) {
        
        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("name") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postItemSubject());

                    preparedStatement.setString(1, request.queryParams("name"));
                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }


    /**
     * Создает замену для конкретного предмета в расписании группы.
     * Используется таблица "change"
     *
     * @param connection
     * @return
     */
    public static String postChange(HashMap<String, Connection> connection, Request request, Response response) {

        if (TokenCheck.checkAdmin(connection, request.queryParams("token"))) {

            if (request.queryParams("id_list_subject") != null &&
                    request.queryParams("t") != null &&
                    request.queryParams("id_account") != null) {

                try {
                    PreparedStatement preparedStatement = connection.get("oqu").prepareStatement(POSTStatement.postChange());

                    preparedStatement.setInt(1, Integer.parseInt(request.queryParams("id_list_subject")));
                    preparedStatement.setInt(2, Integer.parseInt(request.queryParams("t")));
                    preparedStatement.setInt(3, Integer.parseInt(request.queryParams("id_account")));

                    if (request.queryParams("id_room") != null)
                        preparedStatement.setInt(4, Integer.parseInt(request.queryParams("id_room")));
                    else
                        preparedStatement.setInt(4, 0);

                    preparedStatement.execute();

                    response.status(201);
                } catch (SQLException | NumberFormatException e) {

                    response.status(400);

                    return StatusResponse.ERROR;
                }

                return StatusResponse.SUCCESS;
            } else {

                response.status(400);

                return StatusResponse.ERROR;
            }
        } else {

            response.status(401);

            return StatusResponse.ERROR;
        }
    }

    /**
     * Создает расписание для группы.
     * Используется таблица "schedule"
     *
     * @param connection
     * @return
     */
    public static String postSchedule(HashMap<String, Connection> connection, Request request) {
        return "OquPOST postSchedule";
    }
}
