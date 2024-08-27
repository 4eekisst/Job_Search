<#import "../layout.ftlh" as main>
<@main.layout>
    <main class="container mt-4">
        <form method="get" action="/vacancies" class="form-inline mb-3">
            <input type="hidden" name="page" value="0">
            <input type="hidden" name="size" value="${vacanciesPage.size}">
            <input type="hidden" name="previousSortBy" value="${sortBy}">
            <label for="sortBy" class="mr-2">Сортировать:</label>
            <select class="custom-select" name="sortBy" id="sortBy" onchange="this.form.submit()">
                <option value="date" <#if sortBy == "date">selected</#if>>По дате публикации</option>
                <option value="responses" <#if sortBy == "responses">selected</#if>>По количеству откликов</option>
            </select>
        </form>
        <div class="row mt-4">
            <#list vacanciesPage.content as vacancy>
                <div class="col-sm-6 mb-4">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="card-title mb-0">${vacancy.name}</h5>
                        </div>
                        <div class="card-body">
                            <p class="card-text">
                                <strong>Требуемый опыт:</strong>
                                <#if vacancy.expFrom == vacancy.expTo>
                                    <#if vacancy.expFrom % 10 == 1 && vacancy.expFrom % 100 != 11>
                                        ${vacancy.expFrom} год
                                    <#elseif (vacancy.expFrom % 10 >= 2 && vacancy.expFrom % 10 <= 4) && (vacancy.expFrom % 100 < 10 || vacancy.expFrom % 100 >= 20)>
                                        ${vacancy.expFrom} года
                                    <#else>
                                        ${vacancy.expFrom} лет
                                    </#if>
                                <#else>
                                    <#if vacancy.expFrom % 10 == 1 && vacancy.expFrom % 100 != 11>
                                        ${vacancy.expFrom} год
                                    <#elseif (vacancy.expFrom % 10 >= 2 && vacancy.expFrom % 10 <= 4) && (vacancy.expFrom % 100 < 10 || vacancy.expFrom % 100 >= 20)>
                                        ${vacancy.expFrom} года
                                    <#else>
                                        ${vacancy.expFrom} лет
                                    </#if>
                                    -
                                    <#if vacancy.expTo % 10 == 1 && vacancy.expTo % 100 != 11>
                                        ${vacancy.expTo} год
                                    <#elseif (vacancy.expTo % 10 >= 2 && vacancy.expTo % 10 <= 4) && (vacancy.expTo % 100 < 10 || vacancy.expTo % 100 >= 20)>
                                        ${vacancy.expTo} года
                                    <#else>
                                        ${vacancy.expTo} лет
                                    </#if>
                                </#if>
                            </p>
                            <p class="card-text">${vacancy.description}</p>
                            <div class="mt-auto">
                                <a href="/vacancy/info/${vacancy.id}" class="btn btn-primary">Подробнее</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <div class="container mt-4">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <#if vacanciesPage.hasPrevious()>
                        <li class="page-item">
                            <a class="page-link" href="?page=${vacanciesPage.number - 1}&size=${vacanciesPage.size}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    <#else>
                        <li class="page-item disabled">
                            <span class="page-link" aria-label="Previous">&laquo;</span>
                        </li>
                    </#if>

                    <#list 0..(vacanciesPage.totalPages - 1) as i>
                        <li class="page-item <#if i == vacanciesPage.number>active</#if>">
                            <a class="page-link" href="?page=${i}&size=${vacanciesPage.size}">${i + 1}</a>
                        </li>
                    </#list>

                    <#if vacanciesPage.hasNext()>
                        <li class="page-item">
                            <a class="page-link" href="?page=${vacanciesPage.number + 1}&size=${vacanciesPage.size}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    <#else>
                        <li class="page-item disabled">
                            <span class="page-link" aria-label="Next">&raquo;</span>
                        </li>
                    </#if>
                </ul>
            </nav>
        </div>
    </main>
</@main.layout>
