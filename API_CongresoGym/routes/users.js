'use strict';

const express = require('express');
const router = express.Router();
const usersService = require ( './users-service' ) ;

/******** MOVIES *********/

router.get('/', function (req, res) {
    usersService.getAll((err, users) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (users === null){
                res.status(500).send({
                    msg: "users null"
                });
            } else if (users !== null) {
                res.status(200).send(users);
            }
        }
    );
});

router.post('/', function (req, res) {
    let user = req.body;
    usersService.add(user, (err, user) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (user !== null) {
                res.send({
                    msg: 'User created!'
                });
            }
        }
    );
});

router.delete('/', function (req, res) {
    usersService.removeAll((err) => {
        if (err) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'Users deleted!'
            });
        }
    });
});

/****** MOVIES/title *******/

router.get('/:_id', function (req, res) {
    let _id = req.params._id;
    usersService.get(_id, (err, user) => {
            if (err) {
                res.status(500).send({
                    msg: err
                });
            } else if (user === null){
                res.status(500).send({
                    msg: "users null"
                });
            } else if (user !== null) {
                res.status(200).send(user);
            }
        }
    );
});

router.put('/:_id', function (req, res) {
    const _id = req.params._id;
    const updatedUser = req.body;
    usersService.update(_id, updatedUser, (err, numUpdates) => {
        if (err || numUpdates === 0) {
            res.status(500).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'User updated!'
            });
        }
    });
});

router.delete('/:_id', function (req, res) {
    let _id = req.params._id;
    usersService.remove(_id, (err) => {
        if (err) {
            res.status(404).send({
                msg: err
            });
        } else {
            res.status(200).send({
                msg: 'User deleted!'
            });
        }
    });
});

module.exports = router;
