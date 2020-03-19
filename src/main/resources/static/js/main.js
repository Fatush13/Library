function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var bookApi = Vue.resource('/book{/id}');

Vue.component('message-form', {
    props: ['books', 'bookAt', 'borrowers'],
    data: function () {
        return {
            id: '',
            name: '',
            age: '',
        }
    },
    watch: {
        bookAt: function (newVal, oldVal) {
            this.id = newVal.id;
            this.name = newVal.name;
            this.age = newVal.age;
            this.registerDate = newVal.registerDate;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Type new name" v-model="name" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var book = {
                id: this.id, name: this.name,
                age: this.age, registerDate: this.registerDate
            };

            if (this.id) {
                bookApi.update({id: this.id}, book).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.books, data.id);
                        this.books.splice(index, 1, data);

                    }))
            } else {
                bookApi.save({}, book).then(result =>
                    result.json().then(data => {
                        this.books.push(data);

                    })
                )
            }
        }
    }
});

Vue.component('message-row', {
    props: ['book', 'editBook', 'books'],
    template: '<div>' +
        '<i>({{book.id}})</i>' +
        '{{book.name}}, {{book.registerDate}}, {{book.borrower}}, {{book.borrowDate}}' +
        '<span style="position: absolute; right:0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editBook(this.book);
        },
        del: function () {
            bookApi.remove({id: this.book.id}).then(result => {
                if (result.ok) {
                    this.books.splice(this.books.indexOf(this.book), 1)
                }
            })
        }
    }
});

Vue.component('messages-list', {
    props: ['books'],
    data: function () {
        return {
            book: null
        }
    },
    template:
        '<div style="position: relative">' +
        '<message-form :books="books" :bookAt="book" />' +
        '<message-row v-for="book in books" :key="book.id" :book="book" ' +
        ':editBook="editBook" :books="books"/>' +
        '</div>',
    created: function () {
        bookApi.get().then(result =>
            result.json().then(data =>
                data.forEach(book => this.books.push(book))
            )
        )
    },
    methods: {
        editBook: function (book) {
            this.book = book;
        }
    }
});

var bookApp = new Vue({
    el: '#app',
    template: '<messages-list :books="books" />',
    data: {
        books: []
    }
});