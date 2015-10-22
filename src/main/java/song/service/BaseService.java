package song.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by Song on 2015/10/19.
 */
@Transactional
public abstract  class BaseService<T,ID extends Serializable>{

    private Logger logger = LoggerFactory.getLogger(getClass());


    private  CrudRepository<T,ID> crudRepository ;



    /**
     * 注入CrudRepository
     * 必须由子类调用
     * @param crudRepository
     */
    public  void setCrudRepository(CrudRepository<T,ID> crudRepository){
        this.crudRepository = crudRepository;
    }

    public CrudRepository<T, ID> getCrudRepository() {
        return crudRepository;
    }

    public <S extends T> S save(S entity){
        if(entity==null)return null;
        S e  = crudRepository.save(entity);
        return e;
    }

    public <S extends T> Iterable<S> save(Iterable<S> entities){
        return crudRepository.save(entities);
    }

    public T findOne(ID id){
       return crudRepository.findOne(id);
    }

    public boolean exists(ID id){
        return crudRepository.exists(id);
    }


    public long count(){
        return crudRepository.count();
    }

    @CacheEvict
    public void delete(ID id){
        crudRepository.delete(id);
    }
    @CacheEvict
    public void delete(T entity){
        crudRepository.delete(entity);
    }

    @CacheEvict
    public void delete(Iterable<? extends T> entities){
        crudRepository.delete(entities);
    }


}
