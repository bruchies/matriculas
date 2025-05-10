import GraficoTopCursosEstadoModalidade from '../components/GraficoTopCursosEstadoModalidade';
import { useNavigate } from 'react-router-dom';
import '../index.css';

export default function RankingEstadoModalidade() {
  const navigate = useNavigate();

  return (
    <div className="container">
      <h2 className="titulo">Ranking de Cursos 2022 por Estado e Modalidade</h2>
      <button className="botao-voltar" onClick={() => navigate('/')}>🔙 Voltar ao Menu</button>
      <GraficoTopCursosEstadoModalidade />
    </div>
  );
}
