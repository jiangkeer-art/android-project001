package com.sapphireStar.dao.impl;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sapphireStar.util.MySqlHelper;
import com.sapphireStar.dao.AdministratorDao;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.entity.User;

import java.sql.SQLException;

public class NormalUserDaoImpl extends MySqlHelper implements NormalUserDao  {

    @Override
    public NormalUser getUserByPhone(String phone) throws SQLException {
        getDatabase();
        String sql = "select * from normal_user where phone = '" + phone + "'";
        preparedStatement = connection.prepareStatement(sql);
        cursor = preparedStatement.executeQuery();
        NormalUser normalUser = null;
        //Cursor cursor = db.query("Normal_User",new String[]{"*"},"phone = "+ phone,null,null,null,null );

        normalUser = new NormalUser();
        cursor.beforeFirst();
        cursor.next();
        normalUser.setPhone(cursor.getString("phone"));
        normalUser.setId(cursor.getString("id"));
        normalUser.setName(cursor.getString("name"));
        normalUser.setIdNumber(cursor.getString("id_number"));
        closeDatabase();
        return normalUser;
    }

    @Override
    public int addUser(User user) throws SQLException {
        getDatabase();
        String sql = "insert into `user`(phone,password,is_administrators) values ("+user.getPhone()+","+ user.getPassword()+",0 ) ";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();

        //        ContentValues valueUser = new ContentValues();
//
//        valueUser.put("phone",user.getPhone());
//        valueUser.put("password",user.getPassword());
//        valueUser.put("is_administrators","0"); //1为管理员，0为普通用户
//        db.insert("User",null,valueUser);
        closeDatabase();
        return 0;
    }

    @Override
    public int addNormalUSer(NormalUser normalUser) throws SQLException {
        getDatabase();
        String sql = "insert into normal_user(phone,id,`name`,id_number) values " +
                "("+normalUser.getPhone()+",'"+ normalUser.getId()+ "','"+ normalUser.getName()+"',"+normalUser.getIdNumber()+" ) ";
//        ContentValues valueNormal_User=new ContentValues();
//        valueNormal_User.put("phone",normalUser.getPhone());
//        valueNormal_User.put("id",normalUser.getId());
//        valueNormal_User.put("name",normalUser.getName());
//        valueNormal_User.put("id_number",normalUser.getIdNumber());
//        db.insert("Normal_User",null,valueNormal_User);
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        closeDatabase();
        return 0;
    }

    @Override
    public int modifyPassword(String phone, String newPassword,String reNewPassword,String oldPassword) throws SQLException {
        getDatabase();
        String sql = "select * from user where phone = " + phone;
        preparedStatement = connection.prepareStatement(sql);
        cursor = preparedStatement.executeQuery();
        cursor.next();
        if(cursor.getString("phone").equals(phone) && cursor.getString("password").equals(oldPassword)){
            if(newPassword.equals(reNewPassword)){
                String update = "update `user` set password = "+ newPassword + " where phone = "+ phone;
                if(preparedStatement.executeUpdate(update)<1){
                    //受影响的行数为0，表示未更新成功，表单数据有误
                    return 1;
                }
            }
        }
//        if(cursor.moveToFirst()){
//            do{
//                if(phone.equals(cursor.getString(0))){
//                    if(oldPassword.equals(cursor.getString(1))){
//                        if(newPassword.equals(reNewPassword)){
//                            ContentValues values = new ContentValues();
//                            values.put("password",newPassword);
//                            db.update("User",values,"phone="+phone,null);
//                        }
//                    }
//                }
//            }while(cursor.moveToNext());
//        }
        closeDatabase();
        return 0;
    }

    @Override
    public Object Login(String phone, String password) throws SQLException {
        String sql = "select * from `user` where phone = '" + phone + "' and password = '" + password + "'";
        Log.d("test", sql);
        getDatabase();
        preparedStatement = connection.prepareStatement(sql);
        cursor = preparedStatement.executeQuery(sql);
        //Cursor cursor = db.query("User",new String[]{"*"},"phone = " + "'" + phone + "'" + " and password = " + "'" + password + "'",null,null,null,null);
        if(!cursor.next()){
            return null;
        }
        cursor.beforeFirst();
        cursor.next();
        if(cursor.getInt("is_administrators") == 1){
            AdministratorDao administratorDao = new AdministratorDaoImpl();
            return administratorDao.getAdministratorByPhone(phone);
        }
        closeDatabase();
        return getUserByPhone(phone);
    }

    @Override
    public int modifyPhone(String oldPhone, String newPhone, String password) throws SQLException {
        getDatabase();
        String sql = "update `user`,normal_user set `user`.phone = '"+ newPhone +"' where `user`.password = '"+ password +"' and `user`.phone = '"+ oldPhone +"' and normal_user.phone = '"+ oldPhone +"'" ;

        preparedStatement = connection.prepareStatement(sql);
        if(preparedStatement.executeUpdate() == 0){
            //受影响的行数为0，表示未更新成功，表单数据有误
            return 1;
        }

        closeDatabase();
        return 0;
    }

    @Override
    public int modifyIdNumber(String phone, String password, String IdNumber, String name) throws SQLException {
        getDatabase();

        String sql = "update normal_user,user set normal_user.name = '"+ name +"',normal_user.id_number = '" + IdNumber + "' where `user`.password = '" + password + "' and `user`.phone = '" + phone + "' and `user`.phone = normal_user.phone" ;
        preparedStatement = connection.prepareStatement(sql);
        if(preparedStatement.executeUpdate() < 0 ){
            return 1;
        }
        closeDatabase();

        return 0;
    }
}
