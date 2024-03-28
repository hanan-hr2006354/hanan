

document.addEventListener('DOMContentLoaded', async () => {//asyn for await
    const items=localStorage.getItem('items');

    if(items){
        arrayOfItems=JSON.parse(items)//prevents overwriting changes
    }
    else{
        fetch('data/items.json')
        .then(response => response.json())
        .then(data => {
            arrayOfItems=data;
        localStorage.setItem('items', JSON.stringify(arrayOfItems));//key,v
    });}

    const categoryCards = document.querySelectorAll('.category .card');

    categoryCards.forEach(card => {
        card.addEventListener('click', function () {
            const categoryName = card.id;
            localStorage.setItem('choosenCategory', categoryName);
        });});});

document.querySelector('.logout').addEventListener('click', () => {
    localStorage.removeItem('loggedInUser');
    window.location.href = '/public/login.html'; 
});


