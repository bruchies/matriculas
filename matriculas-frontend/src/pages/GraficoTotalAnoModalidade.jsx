import { useEffect, useState } from 'react';
import { getTotalPorAno } from '../services/api';
import { useNavigate } from 'react-router-dom';
import '../index.css';
import GraficoLinhasAnoModalidade from '../components/GraficoLinhasAnoModalidade';

export default function GraficoTotalAnoModalidade() {
  const [dados, setDados] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    getTotalPorAno().then((res) => setDados(res.data));
  }, []);

  return (
    <div className="container">
      <h2 className="titulo">Total de Matrículas por Ano e Modalidade</h2>
      <button className="botao-voltar" onClick={() => navigate('/')}>🔙 Voltar ao Menu</button>
      <GraficoLinhasAnoModalidade dados={dados} />
    </div>
  );
}
