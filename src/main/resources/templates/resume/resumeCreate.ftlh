<#import "../layout.ftlh" as resumeCreate>
<@resumeCreate.layout>

    <div class="container mt-5">
        <h1>Создание резюме:</h1>
        <form action="/resume/create" method="post">
            <div class="form-group">
                <label for="name">Название резюме:</label>
                <input type="text" class="form-control" id="name" name="resumeDto.name" placeholder="Введите название" required>
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
                <label for="salary">Ожидаемый уровень оклада:</label>
                <input type="number" step="0.01" class="form-control" id="salary" name="resumeDto.salary" placeholder="Введите оклад" required>
            </div>


            <h3>О себе:</h3>
            <div id="workExperienceSection">
                <div class="form-group">
                    <label for="years">Год работы:</label>
                    <input type="text" class="form-control" id="years" name="workExperienceInfoDtos[0].years">
                </div>

                <div class="form-group">
                    <label for="companyName">Название компании:</label>
                    <input type="text" class="form-control" id="companyName" name="workExperienceInfoDtos[0].companyName">
                </div>

                <div class="form-group">
                    <label for="position">Должность:</label>
                    <input type="text" class="form-control" id="position" name="workExperienceInfoDtos[0].position">
                </div>

                <div class="form-group">
                    <label for="responsibilities">Обязанности:</label>
                    <textarea class="form-control" id="responsibilities" name="workExperienceInfoDtos[0].responsibilities"></textarea>
                </div>
            </div>

            <div id="educationSection">
                <div class="form-group">
                    <label for="degree">Степень:</label>
                    <input type="text" class="form-control" id="degree" name="educationInfoDtos[0].degree">
                </div>

                <div class="form-group">
                    <label for="institution">Учебное заведение:</label>
                    <input type="text" class="form-control" id="institution" name="educationInfoDtos[0].institution">
                </div>
                <div class="form-group">
                    <label for="program">Программа:</label>
                    <input type="text" class="form-control" id="program" name="educationInfoDtos[0].program">
                </div>

                <div class="form-group">
                    <label for="startDate">Дата начала:</label>
                    <input type="date" class="form-control" id="startDate" name="educationInfoDtos[0].startDate">
                </div>
                <div class="form-group">
                    <label for="endDate">Дата окончания:</label>
                    <input type="date" class="form-control" id="endDate" name="educationInfoDtos[0].endDate">
                </div>

            </div>

            <div id="contactInfoSection">
                <h3>Контактная информация:</h3>
                <div class="form-group">
                    <label for="telegram">Telegram</label>
                    <input type="text" class="form-control" id="telegram" name="contactInfoDtos[0].value" placeholder="Введите Telegram">
                    <input type="hidden" name="contactInfoDtos[0].typeId" value="1">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="contactInfoDtos[1].value" placeholder="Введите Email">
                    <input type="hidden" name="contactInfoDtos[1].typeId" value="2">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="phoneNumber" name="contactInfoDtos[2].value" placeholder="Введите номер телефона">
                    <input type="hidden" name="contactInfoDtos[2].typeId" value="3">
                </div>
                <div class="form-group">
                    <label for="facebook">Facebook</label>
                    <input type="text" class="form-control" id="facebook" name="contactInfoDtos[3].value" placeholder="Введите Facebook">
                    <input type="hidden" name="contactInfoDtos[3].typeId" value="4">
                </div>
                <div class="form-group">
                    <label for="linkedin">LinkedIn</label>
                    <input type="text" class="form-control" id="linkedin" name="contactInfoDtos[4].value" placeholder="Введите LinkedIn">
                    <input type="hidden" name="contactInfoDtos[4].typeId" value="5">
                </div>
            </div>



            <div class="form-group">
                <label for="isActive">Активно:</label>
                <input type="checkbox" class="form-control" id="isActive" name="resumeDto.isActive">
            </div>

            <button type="submit" class="btn btn-primary">Создать резюме</button>
        </form>
    </div>

</@resumeCreate.layout>
