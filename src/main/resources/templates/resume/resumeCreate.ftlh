<#import "../layout.ftlh" as resumeCreate>
<@resumeCreate.layout ; spring>

    <div class="container mt-5">
        <h1><@spring.message'resumeCreate.create'/></h1>
        <form action="/resume/create" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="form-group">
                <label for="name"><@spring.message'resumeCreate.name'/></label>
                <input type="text" class="form-control" id="name" name="resumeDto.name" placeholder="<@spring.message'resumeCreate.name.placeholder'/>" required>
            </div>

            <div class="form-group">
                <label for="categoryId" class="form-label"><@spring.message'resumeCreate.category'/></label>
                <select id="categoryId" name="resumeDto.categoryId" class="form-select" required>
                    <option value="" disabled selected><@spring.message'resumeCreate.choose.category'/></option>
                    <#list categories as category>
                        <option value="${category.id}">${category.name}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label for="salary"><@spring.message'resumeCreate.salary'/></label>
                <input type="number" step="0.01" class="form-control" id="salary" name="resumeDto.salary" placeholder="<@spring.message'resumeCreate.salary.placeholder'/>" required>
            </div>


            <h3><@spring.message'resumeCreate.about'/></h3>
            <div id="workExperienceSection">
                <div class="form-group">
                    <label for="years"><@spring.message'resumeCreate.year.of.work'/></label>
                    <input type="text" class="form-control" id="years" name="workExperienceInfoDtos[0].years">
                </div>

                <div class="form-group">
                    <label for="companyName"><@spring.message'resumeCreate.company.name'/></label>
                    <input type="text" class="form-control" id="companyName" name="workExperienceInfoDtos[0].companyName">
                </div>

                <div class="form-group">
                    <label for="position"><@spring.message'resumeCreate.position'/></label>
                    <input type="text" class="form-control" id="position" name="workExperienceInfoDtos[0].position">
                </div>

                <div class="form-group">
                    <label for="responsibilities"><@spring.message'resumeCreate.responsibilities'/></label>
                    <textarea class="form-control" id="responsibilities" name="workExperienceInfoDtos[0].responsibilities"></textarea>
                </div>
            </div>

            <div id="educationSection">
                <div class="form-group">
                    <label for="degree"><@spring.message'resumeCreate.degree'/></label>
                    <input type="text" class="form-control" id="degree" name="educationInfoDtos[0].degree">
                </div>

                <div class="form-group">
                    <label for="institution"><@spring.message'resumeCreate.institution'/></label>
                    <input type="text" class="form-control" id="institution" name="educationInfoDtos[0].institution">
                </div>
                <div class="form-group">
                    <label for="program"><@spring.message'resumeCreate.program'/></label>
                    <input type="text" class="form-control" id="program" name="educationInfoDtos[0].program">
                </div>

                <div class="form-group">
                    <label for="startDate"><@spring.message'resumeCreate.start.date'/></label>
                    <input type="date" class="form-control" id="startDate" name="educationInfoDtos[0].startDate">
                </div>
                <div class="form-group">
                    <label for="endDate"><@spring.message'resumeCreate.end.date'/></label>
                    <input type="date" class="form-control" id="endDate" name="educationInfoDtos[0].endDate">
                </div>

            </div>

            <div id="contactInfoSection">
                <h3><@spring.message'resumeCreate.contact.info'/></h3>
                <div class="form-group">
                    <label for="telegram">Telegram</label>
                    <input type="text" class="form-control" id="telegram" name="contactInfoDtos[0].value" placeholder="<@spring.message'resumeCreate.telegram.placeholder'/>">
                    <input type="hidden" name="contactInfoDtos[0].typeId" value="1">
                </div>
                <div class="form-group">
                    <label for="email"><@spring.message'resumeCreate.email'/></label>
                    <input type="email" class="form-control" id="email" name="contactInfoDtos[1].value" placeholder="<@spring.message'resumeCreate.email.placeholder'/>">
                    <input type="hidden" name="contactInfoDtos[1].typeId" value="2">
                </div>
                <div class="form-group">
                    <label for="phoneNumber"><@spring.message'resumeCreate.phone.number'/></label>
                    <input type="text" class="form-control" id="phoneNumber" name="contactInfoDtos[2].value" placeholder="<@spring.message'resumeCreate.phone.placeholder'/>">
                    <input type="hidden" name="contactInfoDtos[2].typeId" value="3">
                </div>
                <div class="form-group">
                    <label for="facebook">Facebook</label>
                    <input type="text" class="form-control" id="facebook" name="contactInfoDtos[3].value" placeholder="<@spring.message'resumeCreate.facebook.placeholder'/>">
                    <input type="hidden" name="contactInfoDtos[3].typeId" value="4">
                </div>
                <div class="form-group">
                    <label for="linkedin">LinkedIn</label>
                    <input type="text" class="form-control" id="linkedin" name="contactInfoDtos[4].value" placeholder="<@spring.message'resumeCreate.LinkedIn.placeholder'/>">
                    <input type="hidden" name="contactInfoDtos[4].typeId" value="5">
                </div>
            </div>



            <div class="form-group">
                <label for="isActive"><@spring.message'resumeCreate.active'/></label>
                <input type="checkbox" class="form-control" id="isActive" name="resumeDto.isActive">
            </div>

            <button type="submit" class="btn btn-primary"><@spring.message'resumeCreate.submit'/></button>
        </form>
    </div>

</@resumeCreate.layout>
