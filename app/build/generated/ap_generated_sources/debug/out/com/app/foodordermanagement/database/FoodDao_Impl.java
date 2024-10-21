package com.app.foodordermanagement.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.app.foodordermanagement.model.Food;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FoodDao_Impl implements FoodDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Food> __insertionAdapterOfFood;

  private final EntityDeletionOrUpdateAdapter<Food> __deletionAdapterOfFood;

  private final EntityDeletionOrUpdateAdapter<Food> __updateAdapterOfFood;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllFood;

  public FoodDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFood = new EntityInsertionAdapter<Food>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `food` (`id`,`name`,`description`,`price`,`image`,`banner`,`category_id`,`sale`,`featured`,`count`,`totalPrice`,`priceOneFood`,`option`,`variant`,`size`,`sugar`,`ice`,`toppingIds`,`note`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Food value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getPrice());
        if (value.getImage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getImage());
        }
        if (value.getBanner() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getBanner());
        }
        stmt.bindLong(7, value.getCategory_id());
        stmt.bindLong(8, value.getSale());
        final int _tmp;
        _tmp = value.isFeatured() ? 1 : 0;
        stmt.bindLong(9, _tmp);
        stmt.bindLong(10, value.getCount());
        stmt.bindLong(11, value.getTotalPrice());
        stmt.bindLong(12, value.getPriceOneFood());
        if (value.getOption() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getOption());
        }
        if (value.getVariant() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getVariant());
        }
        if (value.getSize() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getSize());
        }
        if (value.getSugar() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getSugar());
        }
        if (value.getIce() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getIce());
        }
        if (value.getToppingIds() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getToppingIds());
        }
        if (value.getNote() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getNote());
        }
      }
    };
    this.__deletionAdapterOfFood = new EntityDeletionOrUpdateAdapter<Food>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `food` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Food value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfFood = new EntityDeletionOrUpdateAdapter<Food>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `food` SET `id` = ?,`name` = ?,`description` = ?,`price` = ?,`image` = ?,`banner` = ?,`category_id` = ?,`sale` = ?,`featured` = ?,`count` = ?,`totalPrice` = ?,`priceOneFood` = ?,`option` = ?,`variant` = ?,`size` = ?,`sugar` = ?,`ice` = ?,`toppingIds` = ?,`note` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Food value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getPrice());
        if (value.getImage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getImage());
        }
        if (value.getBanner() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getBanner());
        }
        stmt.bindLong(7, value.getCategory_id());
        stmt.bindLong(8, value.getSale());
        final int _tmp;
        _tmp = value.isFeatured() ? 1 : 0;
        stmt.bindLong(9, _tmp);
        stmt.bindLong(10, value.getCount());
        stmt.bindLong(11, value.getTotalPrice());
        stmt.bindLong(12, value.getPriceOneFood());
        if (value.getOption() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getOption());
        }
        if (value.getVariant() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getVariant());
        }
        if (value.getSize() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getSize());
        }
        if (value.getSugar() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getSugar());
        }
        if (value.getIce() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getIce());
        }
        if (value.getToppingIds() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getToppingIds());
        }
        if (value.getNote() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getNote());
        }
        stmt.bindLong(20, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllFood = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from Food";
        return _query;
      }
    };
  }

  @Override
  public void insertFood(final Food food) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFood.insert(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteFood(final Food food) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfFood.handle(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateFood(final Food food) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfFood.handle(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllFood() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllFood.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllFood.release(_stmt);
    }
  }

  @Override
  public List<Food> getListFoodCart() {
    final String _sql = "SELECT * FROM food";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
      final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfSale = CursorUtil.getColumnIndexOrThrow(_cursor, "sale");
      final int _cursorIndexOfFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "featured");
      final int _cursorIndexOfCount = CursorUtil.getColumnIndexOrThrow(_cursor, "count");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
      final int _cursorIndexOfPriceOneFood = CursorUtil.getColumnIndexOrThrow(_cursor, "priceOneFood");
      final int _cursorIndexOfOption = CursorUtil.getColumnIndexOrThrow(_cursor, "option");
      final int _cursorIndexOfVariant = CursorUtil.getColumnIndexOrThrow(_cursor, "variant");
      final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
      final int _cursorIndexOfSugar = CursorUtil.getColumnIndexOrThrow(_cursor, "sugar");
      final int _cursorIndexOfIce = CursorUtil.getColumnIndexOrThrow(_cursor, "ice");
      final int _cursorIndexOfToppingIds = CursorUtil.getColumnIndexOrThrow(_cursor, "toppingIds");
      final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
      final List<Food> _result = new ArrayList<Food>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Food _item;
        _item = new Food();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final int _tmpPrice;
        _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item.setImage(_tmpImage);
        final String _tmpBanner;
        _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
        _item.setBanner(_tmpBanner);
        final int _tmpCategory_id;
        _tmpCategory_id = _cursor.getInt(_cursorIndexOfCategoryId);
        _item.setCategory_id(_tmpCategory_id);
        final int _tmpSale;
        _tmpSale = _cursor.getInt(_cursorIndexOfSale);
        _item.setSale(_tmpSale);
        final boolean _tmpFeatured;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfFeatured);
        _tmpFeatured = _tmp != 0;
        _item.setFeatured(_tmpFeatured);
        final int _tmpCount;
        _tmpCount = _cursor.getInt(_cursorIndexOfCount);
        _item.setCount(_tmpCount);
        final int _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getInt(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final int _tmpPriceOneFood;
        _tmpPriceOneFood = _cursor.getInt(_cursorIndexOfPriceOneFood);
        _item.setPriceOneFood(_tmpPriceOneFood);
        final String _tmpOption;
        _tmpOption = _cursor.getString(_cursorIndexOfOption);
        _item.setOption(_tmpOption);
        final String _tmpVariant;
        _tmpVariant = _cursor.getString(_cursorIndexOfVariant);
        _item.setVariant(_tmpVariant);
        final String _tmpSize;
        _tmpSize = _cursor.getString(_cursorIndexOfSize);
        _item.setSize(_tmpSize);
        final String _tmpSugar;
        _tmpSugar = _cursor.getString(_cursorIndexOfSugar);
        _item.setSugar(_tmpSugar);
        final String _tmpIce;
        _tmpIce = _cursor.getString(_cursorIndexOfIce);
        _item.setIce(_tmpIce);
        final String _tmpToppingIds;
        _tmpToppingIds = _cursor.getString(_cursorIndexOfToppingIds);
        _item.setToppingIds(_tmpToppingIds);
        final String _tmpNote;
        _tmpNote = _cursor.getString(_cursorIndexOfNote);
        _item.setNote(_tmpNote);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Food> checkFoodInCart(final int id) {
    final String _sql = "SELECT * FROM food WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
      final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfSale = CursorUtil.getColumnIndexOrThrow(_cursor, "sale");
      final int _cursorIndexOfFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "featured");
      final int _cursorIndexOfCount = CursorUtil.getColumnIndexOrThrow(_cursor, "count");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
      final int _cursorIndexOfPriceOneFood = CursorUtil.getColumnIndexOrThrow(_cursor, "priceOneFood");
      final int _cursorIndexOfOption = CursorUtil.getColumnIndexOrThrow(_cursor, "option");
      final int _cursorIndexOfVariant = CursorUtil.getColumnIndexOrThrow(_cursor, "variant");
      final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
      final int _cursorIndexOfSugar = CursorUtil.getColumnIndexOrThrow(_cursor, "sugar");
      final int _cursorIndexOfIce = CursorUtil.getColumnIndexOrThrow(_cursor, "ice");
      final int _cursorIndexOfToppingIds = CursorUtil.getColumnIndexOrThrow(_cursor, "toppingIds");
      final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
      final List<Food> _result = new ArrayList<Food>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Food _item;
        _item = new Food();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final int _tmpPrice;
        _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        _item.setImage(_tmpImage);
        final String _tmpBanner;
        _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
        _item.setBanner(_tmpBanner);
        final int _tmpCategory_id;
        _tmpCategory_id = _cursor.getInt(_cursorIndexOfCategoryId);
        _item.setCategory_id(_tmpCategory_id);
        final int _tmpSale;
        _tmpSale = _cursor.getInt(_cursorIndexOfSale);
        _item.setSale(_tmpSale);
        final boolean _tmpFeatured;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfFeatured);
        _tmpFeatured = _tmp != 0;
        _item.setFeatured(_tmpFeatured);
        final int _tmpCount;
        _tmpCount = _cursor.getInt(_cursorIndexOfCount);
        _item.setCount(_tmpCount);
        final int _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getInt(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final int _tmpPriceOneFood;
        _tmpPriceOneFood = _cursor.getInt(_cursorIndexOfPriceOneFood);
        _item.setPriceOneFood(_tmpPriceOneFood);
        final String _tmpOption;
        _tmpOption = _cursor.getString(_cursorIndexOfOption);
        _item.setOption(_tmpOption);
        final String _tmpVariant;
        _tmpVariant = _cursor.getString(_cursorIndexOfVariant);
        _item.setVariant(_tmpVariant);
        final String _tmpSize;
        _tmpSize = _cursor.getString(_cursorIndexOfSize);
        _item.setSize(_tmpSize);
        final String _tmpSugar;
        _tmpSugar = _cursor.getString(_cursorIndexOfSugar);
        _item.setSugar(_tmpSugar);
        final String _tmpIce;
        _tmpIce = _cursor.getString(_cursorIndexOfIce);
        _item.setIce(_tmpIce);
        final String _tmpToppingIds;
        _tmpToppingIds = _cursor.getString(_cursorIndexOfToppingIds);
        _item.setToppingIds(_tmpToppingIds);
        final String _tmpNote;
        _tmpNote = _cursor.getString(_cursorIndexOfNote);
        _item.setNote(_tmpNote);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
