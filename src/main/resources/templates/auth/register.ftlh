<#import "../layout.ftlh" as register>
<@register.layout>

    <div class="container">
        <#assign isEmployer = isEmployer!false>
        <#assign authorityId = userDto.authority_id!1>

        <form action="/auth/register" method="post">
            <div class="form-group">
                <label for="authority_id">Role:</label>
                <select id="authority_id" name="authority_id" class="form-control" onchange="this.form.submit()">
                    <option value="1" <#if authorityId == 1>selected</#if>>Соискатель</option>
                    <option value="2" <#if authorityId == 2>selected</#if>>Работодатель</option>
                </select>
            </div>

            <#if authorityId == 2>
                <div class="form-group">
                    <label for="name">Название компании:</label>
                    <input type="text" id="name" name="name" class="form-control" value="${userDto.name!''}" required>
                </div>
            <#else>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" class="form-control" value="${userDto.name!''}" required>
                </div>

                <div class="form-group">
                    <label for="surname">Surname:</label>
                    <input type="text" id="surname" name="surname" class="form-control" value="${userDto.surname!''}" required>
                </div>
            </#if>

            <div class="form-group">
                <label for="age">Age:</label>
                <input type="number" id="age" name="age" class="form-control" value="${userDto.age!''}" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" class="form-control" value="${userDto.email!''}" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${userDto.phoneNumber!''}">
            </div>

            <div class="form-group">
                <label for="avatar">Avatar URL:</label>
                <input type="text" id="avatar" name="avatar" class="form-control" value="${userDto.avatar!''}">
            </div>

            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

</@register.layout>
