<#import "../layout.ftlh" as listOfVacancy>
<@listOfVacancy.layout>

        <main class="container mt-4">
            <div class="row mt-4">
                <#list vacancies as vacancy>
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
        </main>

</@listOfVacancy.layout>