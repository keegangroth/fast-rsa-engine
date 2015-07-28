require_relative 'setup'

describe 'Cipher' do

  let( :this ) { File.expand_path( '..', __FILE__) }

  let( :private_key ) {
    file = File.read("#{this}/foo.pem")
    OpenSSL::PKey::RSA.new(file)
  }

  let( :msg ) {
    file = File.read("#{this}/foo_cert.pem")
    public_key = OpenSSL::X509::Certificate.new(file).public_key
    public_key.public_encrypt("THIS IS A TEST")
  }

  let( :rounds ) { 100 }

  it 'is faster the regular cipher' do
    skip( 'jruby too old' ) if too_old
    # clear the fast engines
    engines.clear

    start = Time.new.to_f
    rounds.times do
      private_key.private_decrypt(msg)
    end
    delta1 = Time.new.to_f - start

    # setup the fast engines
    engines.clear
    # this creates a warning
    load( "${this}/../lib/fast-rsa-engine.rb" )

    start = Time.new.to_f
    rounds.times do
      private_key.private_decrypt(msg)
    end
    delta2 = Time.new.to_f - start

    expect( delta1 ).to be > 2 * delta2
  end
  
end
