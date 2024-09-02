function updateFormFields() {
    const authorityId = document.getElementById('authorityId').value;
    const dynamicFields = document.getElementById('dynamicFields');

    if (authorityId == '2') {
        dynamicFields.innerHTML = `
            <div class="form-group">
                <label for="name">Название компании:</label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="age">Сколько лет вашей компании:</label>
                <input type="number" id="age" name="age" class="form-control" required>
            </div>
        `;
    } else {
        dynamicFields.innerHTML = `
            <div class="form-group">
                <label for="name">Имя:</label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="surname">Фамилия:</label>
                <input type="text" id="surname" name="surname" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="age">Возраст:</label>
                <input type="number" id="age" name="age" class="form-control" required>
            </div>
        `;
    }
}

document.addEventListener('DOMContentLoaded', updateFormFields);
