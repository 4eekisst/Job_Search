<#import "../layout.ftlh" as resumeEdit>
<@resumeEdit.layout ; spring>

    <main class="container mt-4">
        <h2><@spring.message'resumeEdit.edit'/></h2>
        <form action="/resume/edit" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <input type="hidden" name="id" value="${resumeDto.id}">
            <div class="mb-3">
                <label for="name" class="form-label"><@spring.message'resumeEdit.name'/></label>
                <input type="text" class="form-control" id="name" name="name" value="${resumeDto.name}" required>
            </div>
            <div class="mb-3">
                <label for="categoryId" class="form-label"><@spring.message'resumeEdit.category'/></label>
                <select class="form-select" id="categoryId" name="categoryId" required>
                    <#list categories as category>
                        <option value="${category.id}" <#if resumeDto.categoryId == category.id>selected</#if>>${category.name}</option>
                    </#list>
                </select>
            </div>
            <div class="mb-3">
                <label for="salary" class="form-label"><@spring.message'resumeEdit.salary'/></label>
                <input type="number" step="0.01" class="form-control" id="salary" name="salary" value="${resumeDto.salary}" required>
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="isActive" name="isActive" <#if resumeDto.isActive>checked</#if>>
                <label class="form-check-label" for="isActive"><@spring.message'resumeEdit.acitve'/></label>
            </div>
            <button type="submit" class="btn btn-primary"><@spring.message'resumeEdit.submit'/></button>
        </form>
    </main>

</@resumeEdit.layout>
