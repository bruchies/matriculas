import GraficoTopCursosModalidade from '../components/GraficoTopCursosModalidade';
import { useNavigate } from 'react-router-dom';
import '../index.css';

export default function RankingModalidade() {
  const navigate = useNavigate();

  return (
    <div className="container">
      <h2 className="titulo">Ranking de Cursos 2022 por Modalidade</h2>
      <button className="botao-voltar" onClick={() => navigate('/')}>🔙 Voltar ao Menu</button>
      <GraficoTopCursosModalidade />
    </div>
  );
}
