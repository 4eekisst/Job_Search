<#import "../layout.ftlh" as edit>
<@edit.layout ; spring>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form id="profile-form" action="/edit" method="post" enctype="multipart/form-data">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <div class="mb-3">
                        <label for="avatar-input" class="form-label"><@spring.message'edit.profile.image'/></label>
                        <input name="file" class="form-control" type="file" id="avatar-input">
                    </div>

                    <#if authority_id == 1>
                        <div class="mb-3">
                            <label for="name-input" class="form-label"><@spring.message'edit.profile.name'/></label>
                            <input name="name" class="form-control" type="text" id="name-input" value="${user.name}">
                        </div>
                        <div class="mb-3">
                            <label for="surname-input" class="form-label"><@spring.message'edit.profile.surname'/></label>
                            <input name="surname" class="form-control" type="text" id="surname-input" value="${user.surname}">
                        </div>
                        <div class="mb-3">
                            <label for="age-input" class="form-label"><@spring.message'edit.profile.age'/></label>
                            <input name="age" class="form-control" type="number" id="age-input" value="${user.age}">
                        </div>
                    <#else>
                        <div class="mb-3">
                            <label for="company-name-input" class="form-label"><@spring.message'edit.profile.company.name'/></label>
                            <input name="name" class="form-control" type="text" id="company-name-input" value="${user.name}">
                        </div>
                        <div class="mb-3">
                            <label for="company-age-input" class="form-label"><@spring.message'edit.profile.company.age'/></label>
                            <input name="age" class="form-control" type="number" id="company-age-input" value="${user.age}">
                        </div>
                    </#if>

                    <div class="mb-3">
                        <label for="phone-input" class="form-label"><@spring.message'edit.profile.phone.number'/></label>
                        <input name="phoneNumber" class="form-control" type="text" id="phone-input" value="${user.phoneNumber}">
                    </div>

                    <button type="submit" class="btn btn-primary"><@spring.message'edit.profile.submit'/></button>
                </form>
                <div class="mt-3">
                    <p class="text-muted"><@spring.message'edit.profile.text'/></p>
                </div>
            </div>
        </div>
    </div>
</@edit.layout>
