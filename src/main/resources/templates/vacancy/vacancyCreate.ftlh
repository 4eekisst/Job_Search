<#import "../layout.ftlh" as createVacancy>
<@createVacancy.layout>

    <div class="container mt-5">
        <h1 class="mb-4">Создание вакансии:</h1>
        <form action="/vacancy/create" method="post">

            <div class="form-group mb-3">
                <label for="name" class="form-label">Название вакансии:</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Введите название" required>
            </div>

            <div class="form-group mb-3">
                <label for="categoryId" class="form-label">Категория:</label>
                <select id="categoryId" name="categoryId" class="form-select" required>
                    <option value="" disabled selected>Выберите категорию</option>
                    <#list categories as category>
                        <option value="${category.id}">${category.name}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group mb-3">
                <label for="salary" class="form-label">Заработная плата:</label>
                <input type="number" id="salary" name="salary" class="form-control" step="0.01" placeholder="Введите оклад" required>
            </div>

            <div class="form-group mb-3">
                <label for="description" class="form-label">Описание вакансии:</label>
                <textarea id="description" name="description" class="form-control" rows="4" required></textarea>
            </div>

            <h4>Требуемый опыт:</h4>
            <div class="form-group mb-3">
                <input type="number" id="expFrom" name="expFrom" class="form-control" placeholder="от" required>
            </div>

            <div class="form-group mb-3">
                <input type="number" id="expTo" name="expTo" class="form-control" placeholder="до" required>
            </div>

            <div class="form-group mb-3 form-check">
                <input type="checkbox" id="isActive" name="isActive" class="form-check-input">
                <label for="isActive" class="form-check-label">Активно</label>
            </div>

            <button type="submit" class="btn btn-primary">Опубликовать</button>
        </form>
    </div>

</@createVacancy.layout>
