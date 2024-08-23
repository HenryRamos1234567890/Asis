package com.ESFE.Asistencias.Servicios.Interfaces;
import com.ESFE.Asistencias.Entidades.DocenteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDocenteGrupoServices {
    Page<DocenteGrupo> BuscarTodosPaginados(Pageable pageable);
    List<DocenteGrupo> ObtenerTodos();
    DocenteGrupo CrearOeditar(DocenteGrupo docenteGrupo);
    Optional<DocenteGrupo> BuscarPorId(Integer id);
    void EliminarPorId(Integer id);
}
