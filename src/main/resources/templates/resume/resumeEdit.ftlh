<#import "../layout.ftlh" as listOfResume>
<@listOfResume.layout>

    <main class="container mt-4">
        <h2>Редактирование резюме</h2>
        <form action="/resume/edit" method="post">
            <input type="hidden" name="id" value="${resumeDto.id}">
            <div class="mb-3">
                <label for="name" class="form-label">Имя</label>
                <input type="text" class="form-control" id="name" name="name" value="${resumeDto.name}" required>
            </div>
            <div class="mb-3">
                <label for="categoryId" class="form-label">Категория</label>
                <select class="form-select" id="categoryId" name="categoryId" required>
                    <#list categories as category>
                        <option value="${category.id}" <#if resumeDto.categoryId == category.id>selected</#if>>${category.name}</option>
                    </#list>
                </select>
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label">Зарплата</label>
                <input type="number" step="0.01" class="form-control" id="salary" name="salary" value="${resumeDto.salary}" required>
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="isActive" name="isActive" <#if resumeDto.isActive>checked</#if>>
                <label class="form-check-label" for="isActive">Активно</label>
            </div>
            <button type="submit" class="btn btn-primary">Сохранить изменения</button>
        </form>
    </main>

</@listOfResume.layout>
