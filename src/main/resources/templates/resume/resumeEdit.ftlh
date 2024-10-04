<#import "../layout.ftlh" as resumeEdit>
<@resumeEdit.layout ; spring>

    <main class="container mt-4">
        <h2><@spring.message'resumeEdit.edit'/></h2>
        <form action="/resume/edit" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <input type="hidden" name="resumeDto.id" value="${resumeDto.id}">
            <div class="mb-3">
                <label for="name" class="form-label"><@spring.message'resumeEdit.name'/></label>
                <input type="text" class="form-control" id="name" name="name" value="${resumeDto.name}" required>
            </div>
            <div class="mb-3">
                <label for="categoryId" class="form-label"><@spring.message'resumeEdit.category'/></label>
                <select class="form-select" id="categoryId" name="resumeDto.categoryId" required>
                    <#list categories as category>
                        <option value="${category.id}" <#if resumeDto.categoryId == category.id>selected</#if>>${category.name}</option>
                    </#list>
                </select>
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label"><@spring.message'resumeEdit.salary'/></label>
                <input type="number" step="0.01" class="form-control" id="salary" name="salary" value="${resumeDto.salary}" required>
            </div>

            <!-- Experience Section -->
            <div class="card mt-5">
                <div class="card-body" id="workExperienceSection">
                    <h3>Опыт работы</h3>
                    <#list workExperienceInfoDtos as experience>
                        <div class="form-group">
                            <label for="years">Год работы:</label>
                            <input type="text" class="form-control" name="workExperienceInfoDtos[${experience_index}].years" value="${experience.years}">
                        </div>
                        <div class="form-group">
                            <label for="companyName">Название компании:</label>
                            <input type="text" class="form-control" name="workExperienceInfoDtos[${experience_index}].companyName" value="${experience.companyName}">
                        </div>
                        <div class="form-group">
                            <label for="position">Должность:</label>
                            <input type="text" class="form-control" name="workExperienceInfoDtos[${experience_index}].position" value="${experience.position}">
                        </div>
                        <div class="form-group">
                            <label for="responsibilities">Обязанности:</label>
                            <input type="text" class="form-control" name="workExperienceInfoDtos[${experience_index}].responsibilities" value="${experience.responsibilities}">
                        </div>
                    </#list>
                </div>
            </div>

            <!-- Education Section -->
            <div class="card mt-5">
                <div class="card-body" id="educationSection">
                    <h3>Образование</h3>
                    <#list educationInfoDtos as education>
                        <div class="form-group">
                            <label for="institution">Учебное заведение:</label>
                            <input type="text" class="form-control" name="educationInfoDtos[${education_index}].institution" value="${education.institution}">
                        </div>
                        <div class="form-group">
                            <label for="degree">Степень:</label>
                            <input type="text" class="form-control" name="educationInfoDtos[${education_index}].degree" value="${education.degree}">
                        </div>
                        <div class="form-group">
                            <label for="program">Программа:</label>
                            <input type="text" class="form-control" name="educationInfoDtos[${education_index}].program" value="${education.program}">
                        </div>
                        <div class="form-group">
                            <label for="startDate">Дата начала:</label>
                            <input type="date" class="form-control" name="educationInfoDtos[${education_index}].startDate" value="${education.startDate}">
                        </div>
                        <div class="form-group">
                            <label for="endDate">Дата окончания:</label>
                            <input type="date" class="form-control" name="educationInfoDtos[${education_index}].endDate" value="${education.endDate}">
                        </div>
                    </#list>
                </div>
            </div>

            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="isActive" name="resumeDto.isActive" <#if resumeDto.isActive>checked</#if>>
                <label class="form-check-label" for="isActive"><@spring.message'resumeEdit.acitve'/></label>
            </div>

            <button type="submit" class="btn btn-primary"><@spring.message'resumeEdit.submit'/></button>
        </form>
    </main>

</@resumeEdit.layout>
