function saveSorting() {
    const sortBy = document.querySelector('select[name="sortBy"]')?.value || 'date';
    const page = document.querySelector('input[name="page"]')?.value || '0';
    const size = document.querySelector('input[name="size"]')?.value || '10';

    const searchCriteria = {
        sortBy: sortBy,
        page: page,
        size: size,
    };

    localStorage.setItem('searchCriteria', JSON.stringify(searchCriteria));
}

function loadSearchCriteria() {
    const searchCriteria = JSON.parse(localStorage.getItem('searchCriteria'));
    if (searchCriteria) {
        if (document.querySelector('select[name="sortBy"]')) {
            document.querySelector('select[name="sortBy"]').value = searchCriteria.sortBy || 'date';
        }
        if (document.querySelector('input[name="page"]')) {
            document.querySelector('input[name="page"]').value = searchCriteria.page || '0';
        }
        if (document.querySelector('input[name="size"]')) {
            document.querySelector('input[name="size"]').value = searchCriteria.size || '10';
        }
    }
}

document.querySelector('select[name="sortBy"]')?.addEventListener('change', function() {
    saveSorting();
    this.form.submit();
});

document.addEventListener('DOMContentLoaded', loadSearchCriteria);

window.addEventListener('storage', function(event) {
    if (event.key === 'searchCriteria') {
        loadSearchCriteria();
        document.querySelector('form').submit();
    }
});
