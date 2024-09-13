<#import "../layout.ftlh" as createVacancy>
<@createVacancy.layout ; spring>

    <div class="container mt-5">
        <h1 class="mb-4"><@spring.message'vacancyCreate.create'/></h1>
        <#if error??>
            <div class="alert alert-danger">${error}</div>
        </#if>
        <form action="/vacancy/create" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="form-group mb-3">
                <label for="name" class="form-label"><@spring.message'vacancyCreate.name'/></label>
                <input type="text" id="name" name="name" class="form-control" placeholder="<@spring.message'vacancyCreate.name.placeholder'/>" required>
            </div>

            <div class="form-group mb-3">
                <label for="categoryId" class="form-label"><@spring.message'vacancyCreate.category'/></label>
                <select id="categoryId" name="categoryId" class="form-select" required>
                    <option value="" disabled selected><@spring.message'vacancyCreate.choose.category'/></option>
                    <#list categories as category>
                        <option value="${category.id}">${category.name}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group mb-3">
                <label for="salary" class="form-label"><@spring.message'vacancyCreate.salary'/></label>
                <input type="number" id="salary" name="salary" class="form-control" step="0.01" placeholder="<@spring.message'vacancyCreate.salary.placeholder'/>" required>
            </div>

            <div class="form-group mb-3">
                <label for="description" class="form-label"><@spring.message'vacancyCreate.description'/></label>
                <textarea id="description" name="description" class="form-control" rows="4" required></textarea>
            </div>

            <h4><@spring.message'vacancyCreate.exp'/></h4>
            <div class="form-group mb-3">
                <input type="number" id="expFrom" name="expFrom" class="form-control" placeholder="<@spring.message'vacancyCreate.exp.from'/>" required>
            </div>

            <div class="form-group mb-3">
                <input type="number" id="expTo" name="expTo" class="form-control" placeholder="<@spring.message'vacancyCreate.exp.to'/>" required>
            </div>

            <div class="form-group mb-3 form-check">
                <input type="checkbox" id="isActive" name="isActive" class="form-check-input">
                <label for="isActive" class="form-check-label"><@spring.message'vacancyCreate.active'/></label>
            </div>

            <button type="submit" class="btn btn-primary"><@spring.message'vacancyCreate.submit'/></button>
        </form>
    </div>

</@createVacancy.layout>
