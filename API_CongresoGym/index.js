const express = require('express');
const app = express();
const logger = require('morgan');
const http = require('http');
const path = require('path');
const PORT = process.env.PORT || 8080;
const bodyParser = require('body-parser');
const baseAPI = '/api/v1';
const cors = require('cors');
const usersService = require('./routes/users-service');
const users = require('./routes/users');

app.use(cors());
app.use(bodyParser.json());
app.use(logger('dev'));
app.use(bodyParser.urlencoded({
    extended: true
}));

/**** Routes ****/
app.use('/users', users);

const server = http.createServer(app);

usersService.connectDb(function (err) {
    if (err) {
        console.log("Could not connect with MongoDB - usersService");
        process.exit(1);
    }

    server.listen(PORT, function () {
        console.log('Server up and running on localhost:' + PORT);
    });
});
