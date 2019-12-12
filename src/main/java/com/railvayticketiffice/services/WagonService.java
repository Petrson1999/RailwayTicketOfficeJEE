package com.railvayticketiffice.services;

import com.railvayticketiffice.dao.jdbcdao.imp.JDBCWagonDao;
import com.railvayticketiffice.dao.jdbcdao.imp.JDBCWagonTypeDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.CrudGenericDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.WagonDao;
import com.railvayticketiffice.dao.jdbcdao.interfaces.WagonTypeDao;
import com.railvayticketiffice.dto.WagonDto;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.entity.WagonType;
import com.railvayticketiffice.enums.DaoType;
import com.railvayticketiffice.exeptions.PersistException;
import com.railvayticketiffice.factory.DaoFactory;
import com.railvayticketiffice.factory.ServiceFactory;
import com.railvayticketiffice.web.form.request.AddWagonForm;
import com.railvayticketiffice.web.form.request.AddWagonTypeForm;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class WagonService {

    private static final Logger LOG = Logger.getLogger(WagonService.class);

    private CrudGenericDao<Wagon, Integer> wagonCrudDao;
    private WagonDao wagonDao;
    private WagonTypeDao wagonTypeDao;
    private CrudGenericDao<WagonType, Integer> wagonTypeCrudDao;


    public WagonService() {
        this.wagonCrudDao = DaoFactory.getEntityDao(DaoType.WAGON);
        this.wagonDao = new JDBCWagonDao();
        this.wagonTypeDao = new JDBCWagonTypeDao();
        this.wagonTypeCrudDao = DaoFactory.getEntityDao(DaoType.WAGON_TYPE);
    }

    public List<Wagon> getTrainWagons(int trainId) {
        List<Wagon> wagons = null;
        try {
            wagons = wagonDao.getTrainWagons(trainId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return wagons;
    }

    public String getWagonType(int wagonId) {
        String typeName = null;
        try {
            typeName = wagonTypeDao.getTypeNameById(wagonId);
        } catch (PersistException e) {
            LOG.error(e);
        }
        return typeName;
    }

    public List<Wagon> getAll() {
        List<Wagon> wagons = null;
        try {
            wagons = wagonCrudDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return wagons;
    }

    public List<WagonType> getAllWagonTypes() {
        List<WagonType> wagonTypes = null;
        try {
            wagonTypes = wagonTypeCrudDao.getAll();
        } catch (PersistException e) {
            LOG.error(e);
        }
        return wagonTypes;
    }

    public boolean addWagonType(AddWagonTypeForm addWagonTypeForm) {
        WagonType wagonType = new WagonType(addWagonTypeForm.getName(), addWagonTypeForm.getSeatsCount());
        try {
            this.wagonTypeCrudDao.persist(wagonType);
            return true;
        } catch (PersistException e) {
            LOG.error(e);
            return false;
        }
    }


    public List<WagonDto> getAllWagonDto() {
        List<WagonDto> wagonDtos = new ArrayList<>();
        List<Wagon> wagons = getAll();
        if (wagons == null) {
            return null;
        }

        for (Wagon wagon : wagons) {
            List<Seat> wagonAllSeats = new SeatService().getWagonSeats(wagon.getId());
            wagonDtos.add(new WagonDto(wagon, getWagonType(wagon.getWagonTypeId()), wagonAllSeats));
        }
        return wagonDtos;
    }

    public boolean addWagon(AddWagonForm addWagonForm) {
        Wagon wagon = new Wagon( addWagonForm.getTypeId(), addWagonForm.getName());
        try {
            Wagon createdWagon = this.wagonCrudDao.persist(wagon);
            addSeatOnNewWagon(createdWagon);
            return true;
        } catch (PersistException e) {
            LOG.error(e);
            return false;
        }
    }

    private void addSeatOnNewWagon(Wagon wagon) {
        WagonType wagonType = null;

        try {
            wagonType = wagonTypeCrudDao.getByPK(wagon.getWagonTypeId());
        } catch (PersistException e) {
            LOG.error(e);
        }
        if (wagonType == null) {
            LOG.error("seats dont added");
            return;
        }
        SeatService seatService = ServiceFactory.getSeatService();
        for (int i = 0; i < wagonType.getNumberOfSeats(); i++) {
            Seat seat = new Seat(wagon.getId(), i + 1);
            seatService.addSeat(seat);
        }

    }
}
