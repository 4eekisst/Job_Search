<#import "../layout.ftlh" as editVacancy>
<@editVacancy.layout>

    <div class="container mt-5">
        <h1 class="mb-4">Редактирование вакансии</h1>
        <form action="/vacancy/edit" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <input type="hidden" id="id" name="id" value="${vacancy.id}">

            <div class="form-group mb-3">
                <label for="name" class="form-label">Название вакансии:</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Введите название" value="${vacancy.name}" required>
            </div>

            <div class="form-group mb-3">
                <label for="categoryId" class="form-label">Категория:</label>
                <select id="categoryId" name="categoryId" class="form-select" required>
                    <option value="" disabled>Выберите категорию</option>
                    <#list categories as category>
                        <option value="${category.id}" <#if category.id == vacancy.categoryId>selected</#if>>${category.name}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group mb-3">
                <label for="salary" class="form-label">Заработная плата:</label>
                <input type="number" id="salary" name="salary" class="form-control" step="0.01" placeholder="Введите оклад" value="${vacancy.salary}" required>
            </div>

            <div class="form-group mb-3">
                <label for="description" class="form-label">Описание вакансии:</label>
                <textarea id="description" name="description" class="form-control" rows="4" required>${vacancy.description}</textarea>
            </div>

            <h4>Требуемый опыт:</h4>
            <div class="form-group mb-3">
                <input type="number" id="expFrom" name="expFrom" class="form-control" placeholder="от" value="${vacancy.expFrom}" required>
            </div>

            <div class="form-group mb-3">
                <input type="number" id="expTo" name="expTo" class="form-control" placeholder="до" value="${vacancy.expTo}" required>
            </div>

            <div class="form-group mb-3 form-check">
                <input type="checkbox" id="isActive" name="isActive" class="form-check-input" <#if vacancy.isActive>checked</#if>>
                <label for="isActive" class="form-check-label">Активно</label>
            </div>

            <button type="submit" class="btn btn-primary">Обновить</button>
        </form>
    </div>

</@editVacancy.layout>
