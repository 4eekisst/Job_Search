<#import "../layout.ftlh" as resumeCreate>
<@resumeCreate.layout>

    <div class="container mt-5">
        <h1>Create Resume</h1>
        <form action="/resume/create" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="resumeDto.name" required>
            </div>

            <div class="form-group">
                <label for="categoryId" class="form-label">Категория:</label>
                <select id="categoryId" name="resumeDto.categoryId" class="form-select" required>
                    <option value="" disabled selected>Выберите категорию</option>
                    <#list categories as category>
                        <option value="${category.id}">${category.name}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label for="salary">Salary</label>
                <input type="number" step="0.01" class="form-control" id="salary" name="resumeDto.salary" required>
            </div>

            <div class="form-group">
                <label for="isActive">Is Active</label>
                <input type="checkbox" class="form-control" id="isActive" name="resumeDto.isActive">
            </div>

            <h3>Education Information</h3>
            <div id="educationSection">
                <div class="form-group">
                    <label for="institution">Institution</label>
                    <input type="text" class="form-control" id="institution" name="educationInfoDtos[0].institution">
                </div>
                <div class="form-group">
                    <label for="program">Program</label>
                    <input type="text" class="form-control" id="program" name="educationInfoDtos[0].program">
                </div>
                <div class="form-group">
                    <label for="startDate">Start Date</label>
                    <input type="date" class="form-control" id="startDate" name="educationInfoDtos[0].startDate">
                </div>
                <div class="form-group">
                    <label for="endDate">End Date</label>
                    <input type="date" class="form-control" id="endDate" name="educationInfoDtos[0].endDate">
                </div>
                <div class="form-group">
                    <label for="degree">Degree</label>
                    <input type="text" class="form-control" id="degree" name="educationInfoDtos[0].degree">
                </div>
            </div>

            <h3>Work Experience</h3>
            <div id="workExperienceSection">
                <div class="form-group">
                    <label for="companyName">Company Name</label>
                    <input type="text" class="form-control" id="companyName" name="workExperienceInfoDtos[0].companyName">
                </div>
                <div class="form-group">
                    <label for="position">Position</label>
                    <input type="text" class="form-control" id="position" name="workExperienceInfoDtos[0].position">
                </div>
                <div class="form-group">
                    <label for="years">Years</label>
                    <input type="number" class="form-control" id="years" name="workExperienceInfoDtos[0].years">
                </div>
                <div class="form-group">
                    <label for="responsibilities">Responsibilities</label>
                    <textarea class="form-control" id="responsibilities" name="workExperienceInfoDtos[0].responsibilities"></textarea>
                </div>
            </div>

            <h3>Contact Information</h3>
            <div id="contactInfoSection">
                <div class="form-group">
                    <label for="contactTypeId">Type</label>
                    <select id="contactTypeId" name="contactInfoDtos[0].typeId" class="form-select" required>
                        <option value="" disabled selected>Select Contact Type</option>
                        <#list contactTypes as contactType>
                            <option value="${contactType.id}">${contactType.type}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="contactValue">Value</label>
                    <input type="text" class="form-control" id="contactValue" name="contactInfoDtos[0].value" required>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Create Resume</button>
        </form>
    </div>

</@resumeCreate.layout>
