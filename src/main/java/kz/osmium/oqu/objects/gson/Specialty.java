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

package kz.osmium.oqu.objects.gson;

import com.google.gson.annotations.SerializedName;

public class Specialty {
    @SerializedName("id_specialty")
    private final int idSpecialty;
    @SerializedName("name")
    private final String name;

    public Specialty(int idSpecialty, String name) {
        this.idSpecialty = idSpecialty;
        this.name = name;
    }
}
