package com.vst.applications.project.service;

import com.vst.applications.project.entity.Cathedra;
import com.vst.applications.project.repository.CathedraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимодействия с entity Cathedra при помощи
 * CathedraRepository
 *
 * @see Cathedra
 * @see CathedraRepository
 * */
@Service
public class CathedraService
{
    //DI CathedraRepository в данный сервис
    @Autowired
    private CathedraRepository cathedraRepository;

    /**
     * Получение кафедр - всех записей из таблицы
     * CATHEDRA
     *
     * @return список объектов - записей из таблицы
     * */
    public List<Cathedra> findAll()
    {
        return cathedraRepository.findAll();
    }

    /**
     * Удаление кафедры из БД по id.
     *
     * @param id - id кафедры, которую необходимо удалить */
    public void deleteCathedra(Long id)
    {
        Optional<Cathedra> cathedraOpt = cathedraRepository.findById(id);

        //Перед удалением кафедры необходимо удалить ссылки на эту кафедру
        //у пользователей
        if (cathedraOpt.isPresent())
        {
            cathedraOpt.get().getUsers().forEach(x -> x.setCathedra(null));

            cathedraRepository.deleteById(id);
        }
    }

    /**
     * Сохранение кафедры в БД.
     *
     * @param cathedra - кафедра, которую необходимо сохранить в БД.
     * */
    public boolean saveCathedra(Cathedra cathedra)
    {
        Cathedra cathedraToCheck = cathedraRepository.findByName(cathedra.getName());

        //Если кафедры с таким именем нет, то добавляем ее,
        //иначе игнорируем попытку добавления
        if (cathedraToCheck == null)
        {
            cathedraRepository.save(cathedra);
            return true;
        }

        return false;
    }

    /**
     * Нахождение кафедры в БД по id.
     * @param id - id по которому необходимо найти кафедру в БД.
     * */
    public Optional<Cathedra> findById(Long id)
    {
        return cathedraRepository.findById(id);
    }
}
