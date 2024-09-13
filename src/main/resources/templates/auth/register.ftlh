<#import "../layout.ftlh" as register>
<@register.layout ; spring>

    <div class="container">
        <#assign isEmployer = isEmployer!false>
        <#assign authorityId = userDto.authorityId!1>

        <form action="/auth/register" method="post" id="registerForm">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="form-group">
                <label for="authorityId"><@spring.message'register.type'/></label>
                <select id="authorityId" name="authorityId" class="form-control" onchange="updateFormFields()">
                    <option value="1" <#if authorityId == 1>selected</#if>><@spring.message'register.applicant'/></option>
                    <option value="2" <#if authorityId == 2>selected</#if>><@spring.message'register.employer'/></option>
                </select>
            </div>

            <div id="dynamicFields">
                <#if authorityId == 2>
                    <div class="form-group">
                        <label for="name"><@spring.message'register.company.age'/></label>
                        <input type="text" id="name" name="name" class="form-control" value="${userDto.name!''}" required>
                    </div>

                    <div class="form-group">
                        <label for="age"><@spring.message'register.company.name'/></label>
                        <input type="number" id="age" name="age" class="form-control" value="${userDto.age!''}" required>
                    </div>
                <#else>
                    <div class="form-group">
                        <label for="name"><@spring.message'register.name'/></label>
                        <input type="text" id="name" name="name" class="form-control" value="${userDto.name!''}" required>
                    </div>

                    <div class="form-group">
                        <label for="surname"><@spring.message'register.surname'/></label>
                        <input type="text" id="surname" name="surname" class="form-control" value="${userDto.surname!''}" required>
                    </div>

                    <div class="form-group">
                        <label for="age"><@spring.message'register.age'/></label>
                        <input type="number" id="age" name="age" class="form-control" value="${userDto.age!''}" required>
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <label for="email"><@spring.message'register.email'/></label>
                <input type="email" id="email" name="email" class="form-control" value="${userDto.email!''}" required>
            </div>

            <div class="form-group">
                <label for="password"><@spring.message'register.password'/></label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="phoneNumber"><@spring.message'register.phone.number'/></label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${userDto.phoneNumber!''}">
            </div>

            <button type="submit" class="btn btn-primary"><@spring.message'register.submit'/></button>
        </form>
    </div>

    <script src="/register.js"></script>
    <script> window.messages = {
            nameLabel: "<@spring.message 'register.name'/>",
            surnameLabel: "<@spring.message 'register.surname'/>",
            ageLabel: "<@spring.message 'register.age'/>",
            companyNameLabel: "<@spring.message 'register.company.name'/>",
            companyAgeLabel: "<@spring.message 'register.company.age'/>"};
    </script>
</@register.layout>
