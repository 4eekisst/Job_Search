<#import "spring.ftl" as spring>
<#macro layout>

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JobSearch</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>

    <nav class="navbar navbar-expand-md navbar-dark bg-primary pt-5 pb-5" data-bs-theme="dark">
        <div class="container">
            <a class="navbar-brand" href="/">Job Search</a>
            <button class="navbar-toggler text-light bg-orange btn-user ms-auto" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav"
                    aria-expanded="false" aria-label="Toggle navigation">
                <i class="bi bi-list-nested"></i>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav fw-medium mx-auto">
                    <li class="mx-3 nav-item">
                        <a class="nav-link" aria-current="page" href="/auth/login">login</a>
                    </li>
                    <li class="mx-3 nav-item">
                        <a class="nav-link" aria-current="page" href="/auth/register">register</a>
                    </li>
                    <li class="mx-3 nav-item">
                        <a class="nav-link" href="/#">текст</a>
                    </li>
                    <li class="mx-3 nav-item">
                        <a class="nav-link" href="/#">текст</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <main class="container mt-4">
        <div class="row">

            <#nested >

        </div>
    </main>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
    </html>

</#macro>