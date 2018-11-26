'use strict';

const MongoClient = require('mongodb').MongoClient;
let db;
let ObjectId = require('mongodb').ObjectID;
const Users = function () {
};

Users.prototype.connectDb = function (callback) {
    MongoClient.connect("mongodb://admin:admin123@ds117334.mlab.com:17334/pnet_congresogym",
        {useNewUrlParser: true},
        function (err, database) {
            if (err) {
                callback(err);
            }

   db = database.db('pnet_congresogym').collection('user');

            callback(err, database);
        });
};

Users.prototype.add = function (user, callback) {
    return db.insert(movie, callback);
};

Users.prototype.get = function (_id, callback) {
    return db.find({_id: ObjectId(_id)}).toArray(callback);
};

Users.prototype.getAll = function (callback) {
    return db.find({}).toArray(callback);
};

Users.prototype.update = function (_id, updatedUser, callback) {
    delete updatedUser._id;
    return db.updateOne({_id: ObjectId(_id)}, {$set: updatedUser}, callback);};

Users.prototype.remove = function (_id, callback) {
    return db.deleteOne({_id: ObjectId(_id)}, callback);
};

Users.prototype.removeAll = function (callback) {
    return db.deleteMany({}, callback);
};

module.exports = new Users();
