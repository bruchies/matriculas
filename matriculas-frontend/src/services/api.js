import axios from 'axios';
const API = import.meta.env.VITE_API_URL;

export const getTotalPorAno = () => axios.get(`${API}/consultas/total-por-ano`);

export const getPorAnoModalidade = (modalidade) =>
  axios.get(`${API}/consultas/total-por-ano/modalidade?modalidade=${modalidade}`);

export const getPorAnoEstado = (estado) =>
  axios.get(`${API}/consultas/total-por-ano/estado?estado=${estado}`);

export const getPorAnoEstadoModalidade = (estado, modalidade) =>
  axios.get(`${API}/consultas/total-por-ano/estado-modalidade?estado=${estado}&modalidade=${modalidade}`);

export const getTopCursos2022 = () =>
  axios.get(`${API}/consultas/ranking/cursos-2022`);

export const getTopCursos2022Modalidade = (modalidade) =>
  axios.get(`${API}/consultas/ranking/cursos-2022/modalidade?modalidade=${modalidade}`);

export const getTopCursosEstado = (estado) =>
  axios.get(`${API}/consultas/top-cursos-2022/estado?estado=${estado}`);

export const getTopCursosEstadoModalidade = (estado, modalidade) =>
  axios.get(`${API}/consultas/top-cursos-2022/estado-modalidade?estado=${estado}&modalidade=${modalidade}`);

export const getHistorico = () =>
  axios.get(`${API}/consultas/historico`);
